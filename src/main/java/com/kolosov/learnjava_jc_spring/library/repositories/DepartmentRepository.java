package com.kolosov.learnjava_jc_spring.library.repositories;

import com.kolosov.learnjava_jc_spring.library.models.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Override
    @EntityGraph(attributePaths = {"employees"})
    List<Department> findAll();
}
