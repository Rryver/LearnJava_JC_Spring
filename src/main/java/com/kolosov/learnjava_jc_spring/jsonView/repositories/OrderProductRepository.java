package com.kolosov.learnjava_jc_spring.jsonView.repositories;

import com.kolosov.learnjava_jc_spring.jsonView.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
