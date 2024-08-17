package com.zote.agent.service.domain.support;

import com.zote.agent.service.domain.models.Agent;
import com.zote.common.utils.exceptions.FunctionalError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AgentSupport {

    public void validatePasswords(String password) {
        if (password == null || password.isEmpty()) {
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
        if (email == null || email.isEmpty()) {
            throw new FunctionalError("Email cannot be empty");
        }
        if (!email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
            throw new FunctionalError("Invalid email address");
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new FunctionalError("Phone number cannot be empty");
        }
        if (!phoneNumber.matches("^[0-9]{9}$")) {
            throw new FunctionalError("phone number must contain at least 9 digits");
        }
        if (phoneNumber.startsWith("0")) {
            throw new FunctionalError("phone number cannot start with 0");
        }
    }

    public void validateData(Agent agent) {
        log.info("validating agent");
        validateEmail(agent.getEmail());
        validatePhoneNumber(agent.getPhoneNumber());
        validatePasswords(agent.getPassword());
    }
}
