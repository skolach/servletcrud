package com.globallogic.edu.controller;

import com.globallogic.edu.entity.User;
import com.globallogic.edu.entity.UserDto;
import com.globallogic.edu.entity.UserDtoMapper;
import com.globallogic.edu.service.UserService;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private UserDtoMapper userDtoMapper = Mappers.getMapper(UserDtoMapper.class);

    @Secured("ROLE_ADMIN")
    @GetMapping("newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "userView";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping()
    public String updateUser(UserDto userDto) {
        userService.save(userDtoMapper.userDtoToUser(userDto));
        return "redirect:/order";
    }
}