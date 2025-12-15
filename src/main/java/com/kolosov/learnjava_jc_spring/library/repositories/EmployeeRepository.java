package com.kolosov.learnjava_jc_spring.library.repositories;

import com.kolosov.learnjava_jc_spring.library.models.Employee;
import com.kolosov.learnjava_jc_spring.library.projections.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e join fetch e.department")
    List<EmployeeProjection> getAll();
}
