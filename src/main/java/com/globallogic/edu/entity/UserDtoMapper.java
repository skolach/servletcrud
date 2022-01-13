package com.globallogic.edu.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper
public interface UserDtoMapper {

    UserDto userToUserDto(User user);
    
    @Mapping(source = "password", target = "password", qualifiedBy = APasswordEncryptionMapper.class)
    User userDtoToUser(UserDto userDto);

    @APasswordEncryptionMapper
    public static String passwordToEncrypted(String rawPassword){
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

}