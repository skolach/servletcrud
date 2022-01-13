package com.globallogic.edu.service;

import com.globallogic.edu.entity.User;
import com.globallogic.edu.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUserName(String login) {
        return userRepository.getUserByUserName(login);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

}