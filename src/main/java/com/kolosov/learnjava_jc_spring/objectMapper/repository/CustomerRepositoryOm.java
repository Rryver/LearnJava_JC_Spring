package com.kolosov.learnjava_jc_spring.objectMapper.repository;

import com.kolosov.learnjava_jc_spring.objectMapper.models.CustomerOm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepositoryOm extends JpaRepository<CustomerOm, Long> {
}
