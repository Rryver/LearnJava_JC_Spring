package com.kolosov.learnjava_jc_spring.controllers;

import com.kolosov.learnjava_jc_spring.services.socialLogin.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal CustomOAuth2User principal, Model model) {
        model.addAttribute("name", principal.getUser().getName());
        model.addAttribute("id", principal.getUser().getId());
        model.addAttribute("email", principal.getUser().getEmail());
        return "user";
    }
}
