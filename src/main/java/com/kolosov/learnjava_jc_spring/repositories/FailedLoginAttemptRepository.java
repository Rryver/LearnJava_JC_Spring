package com.kolosov.learnjava_jc_spring.repositories;

import com.kolosov.learnjava_jc_spring.models.FailedLoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedLoginAttemptRepository extends JpaRepository<FailedLoginAttempt, Long> {

    FailedLoginAttempt findByUser_Id(Long userId);
    FailedLoginAttempt findByUser_Email(String userEmail);
}
