package com.zote.user.service.api.response;


import com.zote.user.service.domain.model.Role;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class RoleResponse {
    private String id;
    private String name;
    private String description;
    private Set<PermissionResponse> permissions;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public static RoleResponse toResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        BeanUtils.copyProperties(role, roleResponse);
        roleResponse.setPermissions(role.getPermissions().stream().map(PermissionResponse::toResponse).collect(Collectors.toSet()));
        return roleResponse;
    }

}
