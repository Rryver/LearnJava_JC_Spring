package com.kolosov.learnjava_jc_spring.services;

import com.kolosov.learnjava_jc_spring.models.User;
import com.kolosov.learnjava_jc_spring.repositories.FailedLoginAttemptRepository;
import com.kolosov.learnjava_jc_spring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final FailedLoginAttemptRepository failedLoginAttemptRepository;

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User blockUser(Long id) {
        log.info(String.format("Blocking user with id: %d", id));
        User user = userRepository.findById(id).orElseThrow();
        user.setIsAccountNonLocked(false);
        return userRepository.save(user);
    }

    @Transactional
    public User unblockUser(Long id) {
        log.info(String.format("Unblocking user with id: %d", id));
        User user = userRepository.findById(id).orElseThrow();
        user.setIsAccountNonLocked(true);
        user.getFailedLoginAttempt().setAttempts(0);
        return userRepository.save(user);
    }
}
