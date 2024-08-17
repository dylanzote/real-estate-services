package com.zote.user.service.infrastructure.outbound.mapper;

import com.zote.user.service.domain.model.Role;
import com.zote.user.service.domain.model.RoleDto;
import com.zote.user.service.infrastructure.outbound.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PermissionMapper.class)
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "permissions", source = "permissions")
//    @Mapping(target = "createdBy", source = "createdBy")
//    @Mapping(target = "createdDate", source = "createdDate")
//    @Mapping(target = "lastModifiedBy", source = "lastModifiedBy")
//    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate")
    Role toDto(RoleEntity roleEntity);

    @Mapping(target = "permissions", source = "permissions")
//    @Mapping(target = "roles", source = "roles")
//    @Mapping(target = "users", source = "users")
//    @Mapping(target = "createdBy", source = "createdBy")
//    @Mapping(target = "createdDate", source = "createdDate")
//    @Mapping(target = "lastModifiedBy", source = "lastModifiedBy")
//    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate")
    RoleEntity toEntity(Role role);
}
