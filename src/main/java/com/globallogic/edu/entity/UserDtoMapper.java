package com.globallogic.edu.entity;

import org.mapstruct.Mapper;

@Mapper
public interface UserDtoMapper {

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);

}