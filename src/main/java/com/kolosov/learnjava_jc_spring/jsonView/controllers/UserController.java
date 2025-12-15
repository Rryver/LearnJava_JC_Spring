package com.kolosov.learnjava_jc_spring.jsonView.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.kolosov.learnjava_jc_spring.common.AbstractRestController;
import com.kolosov.learnjava_jc_spring.common.errors.exceptions.ResourceNotFoundException;
import com.kolosov.learnjava_jc_spring.common.views.View;
import com.kolosov.learnjava_jc_spring.jsonView.models.User;
import com.kolosov.learnjava_jc_spring.jsonView.services.UserService;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserController.REST_URL)
@RequiredArgsConstructor
public class UserController extends AbstractRestController {
    public static final String REST_URL = AbstractRestController.BASE_REST_URL + "/jsonView/users";

    private final UserService userService;

    @GetMapping
    @JsonView(View.UserSummary.class)
    public List<User> usersList() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @JsonView(View.UserDetails.class)
    public User user(@PathVariable(name = "id") Long id) {
        User user = userService.getById(id);
        if (user == null) {
            throw new ResourceNotFoundException(String.format("User with id %s not found", id));
        }
        return user;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody @Validated({Default.class, View.CreateEntity.class}) User user) {
        userService.save(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Validated({Default.class, View.UpdateEntity.class}) User user) {
        userService.update(user.getId(), user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
    }
}
