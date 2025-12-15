package com.kolosov.learnjava_jc_spring.objectMapper.repository;

import com.kolosov.learnjava_jc_spring.objectMapper.models.OrderOm;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepositoryOm extends JpaRepository<OrderOm, Long> {

    @Override
    @EntityGraph(attributePaths = {"products", "customer", "products.product"})
    List<OrderOm> findAll();
}
