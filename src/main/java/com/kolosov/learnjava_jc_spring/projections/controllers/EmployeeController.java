package com.kolosov.learnjava_jc_spring.projections.controllers;

import com.kolosov.learnjava_jc_spring.common.AbstractRestController;
import com.kolosov.learnjava_jc_spring.common.views.View;
import com.kolosov.learnjava_jc_spring.projections.models.Employee;
import com.kolosov.learnjava_jc_spring.projections.projections.EmployeeProjection;
import com.kolosov.learnjava_jc_spring.projections.services.EmployeeService;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EmployeeController.REST_URL)
@RequiredArgsConstructor
public class EmployeeController extends AbstractRestController {
    public static final String REST_URL = AbstractRestController.BASE_REST_URL + "/employees";

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeProjection> list() {
        return employeeService.getAllAsProjections();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable(name = "id") Long id) {
        return employeeService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody @Validated({Default.class, View.CreateEntity.class}) Employee employee) {
        employeeService.save(employee);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable(name = "id") Long id,
                       @RequestBody @Validated({Default.class, View.UpdateEntity.class}) Employee employee
    ) {
        employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "id") Long id) {
        employeeService.deleteById(id);
    }
}
