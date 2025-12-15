package com.kolosov.learnjava_jc_spring.projections.services;

import com.kolosov.learnjava_jc_spring.common.CrudService;
import com.kolosov.learnjava_jc_spring.projections.models.Department;
import com.kolosov.learnjava_jc_spring.projections.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends CrudService<Department, Long> {
    private final DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {
        super(departmentRepository);
        this.departmentRepository = departmentRepository;
    }
}
