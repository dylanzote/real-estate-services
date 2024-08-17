package com.zote.user.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleRequest {
    private String id;
    private String name;
    private String description;
    private Set<String> permissionIds;
}
