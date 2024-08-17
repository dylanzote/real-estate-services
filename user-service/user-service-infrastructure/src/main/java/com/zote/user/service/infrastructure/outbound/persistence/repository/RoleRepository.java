package com.zote.user.service.infrastructure.outbound.persistence.repository;

import com.zote.user.service.infrastructure.outbound.entities.RoleEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    Optional<RoleEntity> findByName(String roleName);

    boolean existsByName(String roleName);
}
