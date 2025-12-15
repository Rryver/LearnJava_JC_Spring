package com.kolosov.learnjava_jc_spring.objectMapper.repository;

import com.kolosov.learnjava_jc_spring.objectMapper.models.ProductOm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryOm extends JpaRepository<ProductOm, Long> {
}
