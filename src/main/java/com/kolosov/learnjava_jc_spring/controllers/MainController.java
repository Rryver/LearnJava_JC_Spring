package com.kolosov.learnjava_jc_spring.controllers;

import com.kolosov.learnjava_jc_spring.services.socialLogin.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        boolean isAuthenticated = false;

        CustomOAuth2User user = CustomOAuth2User.get();
        if (user != null) {
            isAuthenticated = true;
            model.addAttribute("username", user.getUsername());
        }

        model.addAttribute("isAuthenticated", isAuthenticated);
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
