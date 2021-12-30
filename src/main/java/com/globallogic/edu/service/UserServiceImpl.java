package com.globallogic.edu.service;

import com.globallogic.edu.entity.User;
import com.globallogic.edu.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

}