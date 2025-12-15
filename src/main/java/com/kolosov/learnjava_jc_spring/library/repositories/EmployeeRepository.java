package com.kolosov.learnjava_jc_spring.library.repositories;

import com.kolosov.learnjava_jc_spring.library.models.Employee;
import com.kolosov.learnjava_jc_spring.library.projections.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    EmployeeProjection findAllAsProjection();
}
