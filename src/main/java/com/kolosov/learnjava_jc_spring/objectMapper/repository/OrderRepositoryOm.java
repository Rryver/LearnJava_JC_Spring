package com.kolosov.learnjava_jc_spring.objectMapper.repository;

import com.kolosov.learnjava_jc_spring.objectMapper.models.OrderOm;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryOm extends JpaRepository<OrderOm, Long> {

    @Override
    @EntityGraph(attributePaths = {"orderProducts", "customer", "orderProducts.product"})
    List<OrderOm> findAll();

    @Override
    @EntityGraph(attributePaths = {"orderProducts", "customer", "orderProducts.product"})
    Optional<OrderOm> findById(Long id);
}
