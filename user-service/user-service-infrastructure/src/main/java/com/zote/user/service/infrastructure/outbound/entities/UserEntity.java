package com.zote.user.service.infrastructure.outbound.entities;

import com.zote.common.utils.audit.Auditable;
import com.zote.common.utils.models.Gender;
import com.zote.common.utils.models.Status;
import com.zote.user.service.domain.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class UserEntity extends Auditable {
    @Id
    private String id;
    private String keycloakUserId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean emailConfirmed;
    private boolean newUser;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String town;
    private String address;
    private String imageUrl;

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(user, entity);
        Set<RoleEntity> roleEntities = user.getRoles()
                .stream()
                .map(RoleEntity::toEntity)
                .collect(Collectors.toSet());
        entity.setRoles(roleEntities);
        return entity;
    }

    public User toDto() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        user.setRoles(this.getRoles().stream()
                .map(RoleEntity::toDto)
                .collect(Collectors.toSet()));
        return user;
    }

    // Method to get all permissions for the user
    public Set<PermissionEntity> getPermissions() {
        return roles.stream()
                .flatMap(role -> role.getPermissions().stream())
                .collect(Collectors.toSet());
    }
}
