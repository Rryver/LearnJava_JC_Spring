package com.kolosov.learnjava_jc_spring.objectMapper.controllers;

import com.kolosov.learnjava_jc_spring.common.AbstractRestController;
import com.kolosov.learnjava_jc_spring.common.components.JsonHelper;
import com.kolosov.learnjava_jc_spring.common.views.View;
import com.kolosov.learnjava_jc_spring.objectMapper.models.ProductOm;
import com.kolosov.learnjava_jc_spring.objectMapper.services.ProductServiceOm;
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
@RequestMapping(value = ProductControllerOm.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductControllerOm extends AbstractRestController {
    public static final String REST_URL = AbstractRestController.BASE_REST_URL + "/objectMapper/products";

    private final ProductServiceOm productService;
    private final JsonHelper jsonHelper;
    private final Validator validator;

    @GetMapping
    public String all() {
        List<ProductOm> products = productService.getAll();
        return jsonHelper.writeValue(products);
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable(name = "id") Long id) {
        ProductOm product = productService.getById(id);
        return jsonHelper.writeValue(product);
    }

    @PostMapping
    public void create(@RequestBody String requestBody) {
        ProductOm product = jsonHelper.readValue(requestBody, ProductOm.class);

        Set<ConstraintViolation<ProductOm>> errors = validator.validate(product, Default.class, View.CreateEntity.class);
        if (!errors.isEmpty()) {
            throw new ValidationException("Ошибка: " + errors);
        }

        productService.save(product);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable(name = "id") Long id,
                       @RequestBody String requestBody
    ) {
        ProductOm product = jsonHelper.readValue(requestBody, ProductOm.class);

        Set<ConstraintViolation<ProductOm>> errors = validator.validate(product, Default.class, View.UpdateEntity.class);
        if (!errors.isEmpty()) {
            throw new ValidationException("Ошибка: " + errors);
        }

        productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
    }
}
