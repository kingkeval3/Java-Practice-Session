package com.java.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/test")
public class TestController {

    @Value("${spring.application.name}")
    private String applicationName;


    @GetMapping(value="/profile-wise/intro")
    public String introduce() {
        return "Hello World! I am "+applicationName;
    }

}
