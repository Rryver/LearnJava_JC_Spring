package com.kolosov.learnjava_jc_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/secured")
    public String secured() {
        return "secured";
    }
}
