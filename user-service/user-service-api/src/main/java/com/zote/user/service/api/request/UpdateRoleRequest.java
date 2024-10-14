package com.zote.user.service.api.request;

import com.zote.user.service.domain.model.RoleRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.util.Set;

public record UpdateRoleRequest(
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        Set<String> permissionIds
) {

        public RoleRequest toRolerequest() {
                RoleRequest roleRequest = new RoleRequest();
                BeanUtils.copyProperties(this, roleRequest);
                return roleRequest;
        }

}
