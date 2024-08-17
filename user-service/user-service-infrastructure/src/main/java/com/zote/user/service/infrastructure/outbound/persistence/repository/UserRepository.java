package com.zote.user.service.infrastructure.outbound.persistence.repository;

import com.zote.user.service.infrastructure.outbound.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByEmail(String email);

    UserEntity findByPhoneNumber(String phoneNumber);
}
