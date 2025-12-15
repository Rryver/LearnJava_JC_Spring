package com.kolosov.learnjava_jc_spring.jsonView.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.kolosov.learnjava_jc_spring.common.AbstractRestController;
import com.kolosov.learnjava_jc_spring.common.errors.exceptions.ResourceNotFoundException;
import com.kolosov.learnjava_jc_spring.jsonView.models.Order;
import com.kolosov.learnjava_jc_spring.jsonView.services.OrderService;
import com.kolosov.learnjava_jc_spring.common.views.View;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(OrderController.REST_URL)
@RequiredArgsConstructor
public class OrderController extends AbstractRestController {
    public static final String REST_URL = AbstractRestController.BASE_REST_URL + "/orders";

    private final OrderService orderService;

    @GetMapping("/{userId}/list")
    @JsonView(View.UserSummary.class)
    public List<Order> usersList(@PathVariable(name = "userId") Long userId) {
        return orderService.getAll(userId);
    }

    @GetMapping("/{id}")
    @JsonView(View.UserDetails.class)
    public Order user(@PathVariable(name = "id") Long id) {
        Order user = orderService.getById(id);
        if (user == null) {
            throw new ResourceNotFoundException(String.format("Order with id %s not found", id));
        }
        return user;
    }

    @PostMapping("/{userId}/create")
    @ResponseStatus(HttpStatus.OK)
    public void create(@PathVariable(name = "userId") Long userId,
                       @RequestBody @Validated({Default.class, View.CreateEntity.class}) Order order) {
        orderService.save(userId, order);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Validated({Default.class, View.UpdateEntity.class}) Order order) {
        orderService.update(order.getId(), order);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "id") Long id) {
        orderService.deleteById(id);
    }
}
