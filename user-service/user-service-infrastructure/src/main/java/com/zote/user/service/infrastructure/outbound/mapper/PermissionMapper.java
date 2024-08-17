package com.zote.user.service.infrastructure.outbound.mapper;

import com.zote.user.service.domain.model.Permission;
import com.zote.user.service.infrastructure.outbound.entities.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionMapper {

    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

//    @Mapping(target = "roles", source = "roles")
    Permission toDto(PermissionEntity permissionEntity);

//    @Mapping(target = "roles", source = "roles")
    PermissionEntity toEntity(Permission permission);
}
