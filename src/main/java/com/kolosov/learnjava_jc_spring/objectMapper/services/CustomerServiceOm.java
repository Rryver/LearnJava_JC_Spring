package com.kolosov.learnjava_jc_spring.objectMapper.services;

import com.kolosov.learnjava_jc_spring.common.CrudService;
import com.kolosov.learnjava_jc_spring.objectMapper.models.CustomerOm;
import com.kolosov.learnjava_jc_spring.objectMapper.repository.CustomerRepositoryOm;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceOm extends CrudService<CustomerOm, Long> {
    public CustomerServiceOm(CustomerRepositoryOm customerRepository) {
        super(customerRepository);
    }
}
