package com.kolosov.learnjava_jc_spring.jsonView.repositories;

import com.kolosov.learnjava_jc_spring.jsonView.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    @EntityGraph(attributePaths = { "orders" }, type = EntityGraph.EntityGraphType.LOAD)
    List<User> findAll();

    @Override
    @EntityGraph(attributePaths = { "orders", "orders.products" }, type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findById(Long aLong);
}
