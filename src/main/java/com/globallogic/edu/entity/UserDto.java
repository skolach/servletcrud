package com.globallogic.edu.entity;

import java.util.Set;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private boolean enabled;
    private Set<Role> roles;

}