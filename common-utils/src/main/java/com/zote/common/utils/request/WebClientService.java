package com.zote.common.utils.request;

import com.zote.common.utils.exceptions.ErrorMessage;
import com.zote.common.utils.exceptions.FunctionalError;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MultiValuedMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@Slf4j
public class WebClientService implements HttpService{

    private static final String ERROR_MESSAGE = "Error occurred while consuming http request";

    @Override
    public <T> T get(String url, Class<T> responseType) {
        log.info("consuming get api");
        return WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(), clientResponse -> {
                    log.info(ERROR_MESSAGE);
                    var error = clientResponse.bodyToMono(ErrorMessage.class);
                    return error.map(errorMessage -> {
                        log.info("logged error is {}", errorMessage);
                        return new FunctionalError(errorMessage.message());
                    });
                })
                .bodyToMono(responseType)
                .block();
    }

    @Override
    public <T> T get(String url, Class<T> responseType, Map<String, String> headers) {
        return WebClient.create()
                .get()
                .uri(url)
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(), clientResponse -> {
                    log.info(ERROR_MESSAGE);
                    var error = clientResponse.bodyToMono(ErrorMessage.class);
                    return error.map(errorMessage -> {
                        log.info("logged error is {}", errorMessage);
                        return new FunctionalError(errorMessage.message());
                    });
                })
                .bodyToMono(responseType)
                .block();
    }

    @Override
    public <T> T get(String url, Class<T> responseType, Map<String, String> headers, Map<String, String> queryParams) {
        return null;
    }

    @Override
    public <T, R> T post(String url, R request, Class<T> responseType, Map<String, String> headers) {
        return WebClient.create()
                .post()
                .uri(url)
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .bodyValue(request)
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(), clientResponse -> {
                    log.info(ERROR_MESSAGE);
                    var error = clientResponse.bodyToMono(ErrorMessage.class);
                    return error.map(errorMessage -> {
                        log.info("logged error is {}", errorMessage);
                        return new FunctionalError(errorMessage.message());
                    });
                })
                .bodyToMono(responseType)
                .block();
    }

    @Override
    public <T> T post(String url, MultiValueMap<String, String> requestData, Class<T> responseType) {
        return WebClient.create()
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(requestData))
                .retrieve()
                .onStatus(httpStatusCode -> !httpStatusCode.is2xxSuccessful(), clientResponse -> {
                    log.info(ERROR_MESSAGE);
                    var error = clientResponse.bodyToMono(ErrorMessage.class);
                    return error.map(errorMessage -> {
                        log.info("error is {}", errorMessage);
                        return new FunctionalError(errorMessage.message());
                    });
                })
                .bodyToMono(responseType)
                .block();
    }
}
