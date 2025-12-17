package com.kolosov.learnjava_jc_spring.services;

import com.kolosov.learnjava_jc_spring.errors.exceptions.ResourceNotFoundException;
import com.kolosov.learnjava_jc_spring.models.FailedLoginAttempt;
import com.kolosov.learnjava_jc_spring.models.User;
import com.kolosov.learnjava_jc_spring.repositories.FailedLoginAttemptRepository;
import com.kolosov.learnjava_jc_spring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FailedLoginAttemptRepository failedLoginAttemptRepository;

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User blockUser(Long id) {
        return changeBlocking(id, true);
    }

    @Transactional
    public User unblockUser(Long id) {
        return changeBlocking(id, false);
    }

    private User changeBlocking(Long id, boolean isBlocked) {
        User user = userRepository.findById(id).orElseThrow();
        user.setIsAccountNonLocked(!isBlocked);
        return userRepository.save(user);
    }

    public int unsuccessfullAutorize(String email) {
        FailedLoginAttempt failedLoginAttempt = failedLoginAttemptRepository.findByUser_Email(email);
        if (failedLoginAttempt == null) {
            throw new ResourceNotFoundException("Unsuccessfull Authorize; User not found");
        }
        failedLoginAttempt.setAttempts(failedLoginAttempt.getAttempts() + 1);

        return 0;
    }
}
