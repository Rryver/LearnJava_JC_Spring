package com.kolosov.learnjava_jc_spring.library.repositories;

import com.kolosov.learnjava_jc_spring.library.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
