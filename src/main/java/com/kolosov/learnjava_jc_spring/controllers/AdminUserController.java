package com.kolosov.learnjava_jc_spring.controllers;

import com.kolosov.learnjava_jc_spring.models.User;
import com.kolosov.learnjava_jc_spring.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;

    @GetMapping
    @Secured({ "ROLE_MODERATOR", "ROLE_SUPER_ADMIN" })
    public List<User> users() {
        return userService.getAll();
    }

    @PatchMapping("/{userId}/block")
    @Secured({ "ROLE_SUPER_ADMIN" })
    public void blockUser(@PathVariable(name = "userId") Long userId) {
        userService.blockUser(userId);
    }

    @PatchMapping("/{userId}/unblock")
    @Secured({ "ROLE_SUPER_ADMIN" })
    public void unblockUser(@PathVariable(name = "userId") Long userId) {
        userService.unblockUser(userId);
    }
}
