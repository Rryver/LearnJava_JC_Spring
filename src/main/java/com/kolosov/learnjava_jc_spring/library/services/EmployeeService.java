package com.kolosov.learnjava_jc_spring.library.services;

import com.kolosov.learnjava_jc_spring.common.CrudService;
import com.kolosov.learnjava_jc_spring.library.models.Employee;
import com.kolosov.learnjava_jc_spring.library.projections.EmployeeProjection;
import com.kolosov.learnjava_jc_spring.library.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService extends CrudService<Employee, Long> {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeProjection> getAllAsProjections() {
        return employeeRepository.getAll();
    }
}
