package com.zote.user.service.infrastructure.outbound.persistence.port;

import com.zote.common.utils.exceptions.FunctionalError;
import com.zote.user.service.domain.model.User;
import com.zote.user.service.domain.ports.outbound.UserRepositoryPort;
import com.zote.user.service.infrastructure.outbound.entities.UserEntity;
import com.zote.user.service.infrastructure.outbound.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserRepositoryPortImpl implements UserRepositoryPort {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        log.info("Saving user {}", user);
        return userRepository.save(UserEntity.toEntity(user)).toDto();
    }

    @Override
    public void deleteUserById(String userId) {
        log.info("Deleting user with userId {}", userId);
        userRepository.deleteById(userId);
    }

    @Override
    public User findUserById(String userId) {
        log.info("Getting user with userId {}", userId);
        return userRepository.findById(userId).map(UserEntity::toDto)
                .orElseThrow(() -> new FunctionalError("could not find user with userId " + userId));
    }

    @Override
    public User findUserByEmail(String email) {
        log.info("Getting user with email {}", email);
        return userRepository.findByEmail(email).map(UserEntity::toDto)
                .orElseThrow(() -> new FunctionalError("could not find user with email " + email));
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        log.info("Getting user with phoneNumber {}", phoneNumber);
        return userRepository.findByPhoneNumber(phoneNumber).map(UserEntity::toDto)
                .orElseThrow(() -> new FunctionalError("could not find user with phoneNumber " + phoneNumber));
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return userRepository.findAll().stream().map(UserEntity::toDto).toList();
    }

    @Override
    public Page<User> findUsersByPage(Pageable pageable) {
        log.info("Getting all users");
        return userRepository.findAll(pageable).map(UserEntity::toDto);
    }

    @Override
    public boolean existsById(String userId) {
        log.info("Verifies if user exists by id {}", userId);
        return userRepository.existsById(userId);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.info("Verifies if user exists by email {}", email);
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        log.info("Verifies if user exists by phoneNumber {}", phoneNumber);
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existsByUserName(String username) {
        log.info("Verifies if user exists by username {}", username);
        return userRepository.existsByUserName(username);
    }
}
