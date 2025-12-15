package com.kolosov.learnjava_jc_spring.projections.projections;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
    String getPosition();

    @Value("#{target.department.name}")
    String getDepartmentName();
}
