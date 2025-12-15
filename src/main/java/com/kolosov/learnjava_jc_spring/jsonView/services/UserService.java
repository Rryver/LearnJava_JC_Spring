package com.kolosov.learnjava_jc_spring.jsonView.services;

import com.kolosov.learnjava_jc_spring.common.CrudService;
import com.kolosov.learnjava_jc_spring.jsonView.models.User;
import com.kolosov.learnjava_jc_spring.jsonView.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<User, Long> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }
}
