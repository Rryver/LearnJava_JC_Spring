package com.kolosov.learnjava_jc_spring.library.controllers;

import com.kolosov.learnjava_jc_spring.common.AbstractRestController;
import com.kolosov.learnjava_jc_spring.common.views.View;
import com.kolosov.learnjava_jc_spring.library.models.Department;
import com.kolosov.learnjava_jc_spring.library.models.Employee;
import com.kolosov.learnjava_jc_spring.library.projections.EmployeeProjection;
import com.kolosov.learnjava_jc_spring.library.services.DepartmentService;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DepartmentController.REST_URL)
@RequiredArgsConstructor
public class DepartmentController extends AbstractRestController {
    public static final String REST_URL = AbstractRestController.BASE_REST_URL + "/departments";

    private final DepartmentService departmentService;

    @GetMapping
    public List<Department> list() {
        return departmentService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody @Validated({Default.class, View.CreateEntity.class}) Department department) {
        departmentService.save(department);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable(name = "id") Long id,
                       @RequestBody @Validated({Default.class, View.UpdateEntity.class}) Department department
    ) {
        departmentService.update(id, department);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "id") Long id) {
        departmentService.deleteById(id);
    }
}
