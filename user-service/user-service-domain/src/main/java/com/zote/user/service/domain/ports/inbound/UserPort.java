package com.zote.user.service.domain.ports.inbound;

import com.zote.user.service.domain.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserPort {

    User createUser(CreateUserData createUserData);

    User createUserByAdmin(CreateUserData createUserData);

    User updateUser(UserData userData);

    void updateUserPassword(PasswordUpdateData passwordUpdateData);

    void deleteUser(String userId);

    void validateUserEmail(String email);

    User findUserById(String userId);

    User findUserByEmail(String email);

    User findUserByPhoneNumber(String phoneNumber);

    List<User> getAllUsers();

    Page<User> getAllUsersByPage(int pageNo, int sizePerPage, String sortField, Sort.Direction sortDirection);

    String uploadUserImage(String userId,  MultipartFile image);

    String getUserImage(String userId);
}
