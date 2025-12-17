package com.kolosov.learnjava_jc_spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecuredController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
