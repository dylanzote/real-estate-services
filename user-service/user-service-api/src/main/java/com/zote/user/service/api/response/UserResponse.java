package com.zote.user.service.api.response;

import com.zote.common.utils.models.Gender;
import com.zote.common.utils.models.Status;
import com.zote.user.service.domain.model.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private Set<RoleResponse> roles;
    private Status status;
    private boolean emailConfirmed;
    private boolean newUser;
    private Gender gender;
    private String town;
    private String address;
    private String imageUrl;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public static UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        userResponse.setRoles(user.getRoles().stream().map(RoleResponse::toResponse).collect(Collectors.toSet()));
        return userResponse;
    }
}
