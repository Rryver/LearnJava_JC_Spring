package com.kolosov.learnjava_jc_spring.controllers;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class ErrorsController {

    @GetMapping("/access-denied")
    public String accessDenied(Model model) {
        throw new AccessDeniedException("Access denied");
    }
}
