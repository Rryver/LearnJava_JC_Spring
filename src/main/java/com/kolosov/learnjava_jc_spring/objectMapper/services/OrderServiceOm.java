package com.kolosov.learnjava_jc_spring.objectMapper.services;

import com.kolosov.learnjava_jc_spring.common.CrudService;
import com.kolosov.learnjava_jc_spring.objectMapper.dto.OrderForm;
import com.kolosov.learnjava_jc_spring.objectMapper.models.OrderOm;
import com.kolosov.learnjava_jc_spring.objectMapper.repository.OrderRepositoryOm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceOm extends CrudService<OrderOm, Long> {
    public OrderServiceOm(OrderRepositoryOm orderRepository) {
        super(orderRepository);
    }

    @Transactional
    public OrderOm save(OrderForm orderForm) {
        OrderOm orderOm = new OrderOm();
        orderOm.set

        return super.save(orderOm);
    }

    @Override
    @Transactional
    public OrderOm save(OrderOm orderOm) {

        return super.save(orderOm);
    }
}
