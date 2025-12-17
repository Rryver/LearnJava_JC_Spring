package com.kolosov.learnjava_jc_spring.repositories;

import com.kolosov.learnjava_jc_spring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
