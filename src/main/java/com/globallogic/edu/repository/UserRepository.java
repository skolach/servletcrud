package com.globallogic.edu.repository;

import com.globallogic.edu.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

    public User getUserByLogin(String login);

}