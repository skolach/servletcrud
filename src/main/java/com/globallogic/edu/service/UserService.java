package com.globallogic.edu.service;

import com.globallogic.edu.entity.User;

public interface UserService {

    public User findUserByLogin(String login);

}