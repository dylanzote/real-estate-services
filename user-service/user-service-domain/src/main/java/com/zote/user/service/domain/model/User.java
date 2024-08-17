package com.zote.user.service.domain.model;

import com.zote.common.utils.models.Gender;
import com.zote.common.utils.models.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String password;
    private Set<Role> roles;
    private Status status;
    private boolean emailConfirmed;
    private boolean newUser;
    private Gender gender;
    private String town;
    private String address;
    private String imageUrl;
}
