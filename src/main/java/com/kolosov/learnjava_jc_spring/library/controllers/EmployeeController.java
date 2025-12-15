package com.kolosov.learnjava_jc_spring.library.controllers;

import com.kolosov.learnjava_jc_spring.common.AbstractRestController;
import com.kolosov.learnjava_jc_spring.common.views.View;
import com.kolosov.learnjava_jc_spring.library.models.Department;
import com.kolosov.learnjava_jc_spring.library.models.Employee;
import com.kolosov.learnjava_jc_spring.library.services.EmployeeService;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
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
    public List<Employee> list() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable(name = "id") Long id) {
        return employeeService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody @Validated({Default.class, View.CreateEntity.class}) Department book) {
        employeeService.save(book);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable(name = "bookId") Long bookId,
                       @RequestBody @Validated({Default.class, View.UpdateEntity.class}) Department book
    ) {
        employeeService.update(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "bookId") Long bookId) {
        employeeService.deleteById(bookId);
    }
}
