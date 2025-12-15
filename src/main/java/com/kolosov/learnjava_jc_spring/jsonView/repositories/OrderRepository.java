package com.kolosov.learnjava_jc_spring.jsonView.repositories;

import com.kolosov.learnjava_jc_spring.jsonView.models.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Override
    @EntityGraph(attributePaths = {"products", "user"})
    List<Order> findAll();

    @Override
    @EntityGraph(attributePaths = {"products"})
    Optional<Order> findById(Long aLong);

    @Query("select o " +
            "from Order o " +
            "join fetch o.user " +
            "join fetch o.products " +
            "where o.user.id = :userId")
    List<Order> findAllByUserId(Long userId);
}
