package com.zote.user.service.api.response;

import com.zote.common.utils.models.Gender;
import com.zote.common.utils.models.Status;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String town;
    private String address;
    private String password;
    private String image;
    private boolean emailConfirmed;
    private boolean newUser;
    private Set<RoleDto> roles;
    private Status status;
    private String createdDate;
    private String lastModifiedDate;
}
