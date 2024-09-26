package com.zote.user.service.domain.support;

import com.zote.common.utils.config.BeanConfig;
import com.zote.common.utils.exceptions.FunctionalError;
import com.zote.common.utils.models.Status;
import com.zote.user.service.domain.model.CreateUserData;
import com.zote.user.service.domain.model.Role;
import com.zote.user.service.domain.model.User;
import com.zote.user.service.domain.model.UserData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class UserSupport {

    private final BeanConfig config;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void validatePasswords(String password) {
        if (Objects.isNull(password)) {
            throw new FunctionalError("Password cannot be empty");
        }
        if (password.length() < 8) {
            throw new FunctionalError("Password must be at least 8 characters long");
        }
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) {
            throw new FunctionalError("Password must contain at least one lowercase letter, one uppercase letter, one number and one special character");
        }
    }

    public void validateEmail(String email) {
        if (Objects.isNull(email)) {
            throw new FunctionalError("Email cannot be empty");
        }
        if (!email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
            throw new FunctionalError("Invalid email address");
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (Objects.isNull(phoneNumber)) {
            throw new FunctionalError("Phone number cannot be null");
        }
        if (!phoneNumber.matches("^[0-9]{9}$")) {
            throw new FunctionalError("phone number must contain 9 digits");
        }
        if (phoneNumber.startsWith("0")) {
            throw new FunctionalError("phone number cannot start with 0");
        }
    }

    public void validateDate(String date) {
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            throw new FunctionalError("Invalid date");
        }
    }

    public void validateData(CreateUserData user) {
        log.info("validating user data");
        validateEmail(user.getEmail());
        validatePhoneNumber(user.getPhoneNumber());
        validatePasswords(user.getPassword());
        validateDate(user.getDateOfBirth());
    }

    public void validateData(UserData user) {
        log.info("validating user data");
        validateEmail(user.getEmail());
        validatePhoneNumber(user.getPhoneNumber());
    }

    public User buildUser(CreateUserData createUserData, Set<Role> roles) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .firstName(createUserData.getFirstName())
                .lastName(createUserData.getLastName())
                .userName(createUserData.getUserName())
                .email(createUserData.getEmail())
                .password(config.passwordEncoder().encode(createUserData.getPassword()))
                .roles(roles)
                .newUser(true)
                .address(createUserData.getAddress())
                .gender(createUserData.getGender())
                .dateOfBirth(createUserData.getDateOfBirth())
                .phoneNumber(createUserData.getPhoneNumber())
                .status(Status.INACTIVE)
                .town(createUserData.getTown())
                .build();
    }
}
