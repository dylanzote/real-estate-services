package com.zote.user.service.infrastructure.outbound.mapper;

import com.zote.user.service.domain.model.User;
import com.zote.user.service.infrastructure.outbound.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = RoleMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", source = "roles")
    User toDto(UserEntity userEntity);

    @Mapping(target = "roles", source = "roles")
    UserEntity toEntity(User userDTO);
}
