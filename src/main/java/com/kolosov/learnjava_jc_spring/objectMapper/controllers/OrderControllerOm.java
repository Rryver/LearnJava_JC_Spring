package com.kolosov.learnjava_jc_spring.objectMapper.controllers;

import com.kolosov.learnjava_jc_spring.common.AbstractRestController;
import com.kolosov.learnjava_jc_spring.common.components.JsonHelper;
import com.kolosov.learnjava_jc_spring.common.views.View;
import com.kolosov.learnjava_jc_spring.objectMapper.dto.OrderForm;
import com.kolosov.learnjava_jc_spring.objectMapper.models.OrderOm;
import com.kolosov.learnjava_jc_spring.objectMapper.services.OrderServiceOm;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = OrderControllerOm.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderControllerOm extends AbstractRestController {

    public static final String REST_URL = AbstractRestController.BASE_REST_URL + "/objectMapper/orders";

    private final OrderServiceOm orderService;
    private final JsonHelper jsonHelper;
    private final Validator validator;

    @GetMapping
    public String all() {
        List<OrderOm> orders = orderService.getAll();
        return jsonHelper.writeValue(orders);
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable(name = "id") Long id) {
        OrderOm order = orderService.getById(id);
        return jsonHelper.writeValue(order);
    }

    @PostMapping
    public void create(@RequestBody String requestBody) {
        OrderForm order = jsonHelper.readValue(requestBody, OrderForm.class);

        Set<ConstraintViolation<OrderForm>> errors = validator.validate(order, Default.class, View.CreateEntity.class);
        if (!errors.isEmpty()) {
            throw new ValidationException("Ошибка: " + errors);
        }

        orderService.save(order);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody String requestBody) {
        OrderForm order = jsonHelper.readValue(requestBody, OrderForm.class);

        Set<ConstraintViolation<OrderForm>> errors = validator.validate(order, Default.class, View.UpdateEntity.class);
        if (!errors.isEmpty()) {
            throw new ValidationException("Ошибка: " + errors);
        }

        orderService.update(id, order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        orderService.deleteById(id);
    }
}
