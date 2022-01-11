package com.globallogic.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
    
    @GetMapping("/403")
    public String getError403(){
        return "403";
    }

    @PostMapping("/403")
    public String postError403(){
        return "403";
    }

}