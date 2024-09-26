package com.zote.common.utils.files;

import com.zote.common.utils.exceptions.FunctionalError;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class MinioObjectStorage {

    @Value("${minio.object.bucket-name}")
    private String bucketName;

    private final MinioClient minioClient;

    public MinioObjectStorage(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @SneakyThrows
    public void uploadFile(String filePath, String objectName) {
        createBucket(minioClient, bucketName);
        minioClient.uploadObject(UploadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .filename(filePath)
                .build());
        log.info("{} is successfully uploaded as object: {} to bucket: {}", filePath, objectName, bucketName);
    }

    @SneakyThrows
    public void uploadImage(MultipartFile multipartFile, String objectName) {
        log.info("multipart getOriginalFilename: {},  getInputStream: {}, getResource: {},  getName: {}", multipartFile.getOriginalFilename(), multipartFile.getInputStream(), multipartFile.getResource(), multipartFile.getName());
        createBucket(minioClient, bucketName);
        minioClient.putObject(PutObjectArgs.builder()
                       .bucket(bucketName)
                       .object(objectName)
                       .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                       .contentType(multipartFile.getContentType())
                       .build());
        log.info("{} is successfully uploaded as object: {} to bucket: {}", multipartFile.getOriginalFilename(), objectName, bucketName);
    }



    public InputStream getObject(String objectName) {
        GetObjectResponse stream = null;
        try {
            stream = minioClient.getObject(GetObjectArgs.builder().object(objectName).bucket(bucketName).build());
        } catch (InsufficientDataException | InvalidKeyException | IOException |
                 NoSuchAlgorithmException | ServerException | XmlParserException | InvalidResponseException |
                 InternalException e) {
            throw new FunctionalError(e.toString());
        } catch (ErrorResponseException exception) {
            log.error("Invalid key: {}", exception.getMessage());
            throw new FunctionalError("no image for user");
        }
        log.info("stream: {} is successfully downloaded from bucket: {}", stream, bucketName);
        return stream;
    }

    public Iterable<Result<Item>> getObjectsFromBucket(String prefix) {
        return minioClient.listObjects(ListObjectsArgs.builder()
                        .bucket(bucketName)
                        .prefix("agents/de8c4838-a947-45e4-b1a9-d9851802b545")
                .build());
    }

    @SneakyThrows
    public void createBucket(MinioClient minioClient, String bucketName) {
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
            log.info("Creating bucket: " + bucketName);
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } else
            log.info("Bucket: {} already exists", bucketName);
    }

    public String getAgentImageName(String agentId, String agentName) {
        return "agents".concat("/").concat(agentId).concat("_").concat(agentName).concat(".jpg");
    }

    public String getUserImageName(String userId) {
        return "users".concat("/").concat(userId).concat(".jpg");
    }

    @SneakyThrows
    public void deleteObject(String objectName) {
        minioClient.deleteObjectTags(DeleteObjectTagsArgs.builder().bucket(bucketName).object(objectName).build());
        log.info("object: {} is successfully deleted from bucket: {}", objectName, bucketName);
    }

    @SneakyThrows
    public static String convertToBase64(InputStream response)  {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = response.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        }
    }

    @SneakyThrows
    public String getPresignedUrl(String objectName)  {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .expiry(1, TimeUnit.HOURS) // URL valid for 1 hour
                .build());
    }
}
