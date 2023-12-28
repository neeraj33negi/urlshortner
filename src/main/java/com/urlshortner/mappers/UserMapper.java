package com.urlshortner.mappers;

import com.urlshortner.entities.User;
import com.urlshortner.models.responses.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse userToUserResponse(final User user);
}
