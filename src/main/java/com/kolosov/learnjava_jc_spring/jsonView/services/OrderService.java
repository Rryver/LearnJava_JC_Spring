package com.kolosov.learnjava_jc_spring.jsonView.services;

import com.kolosov.learnjava_jc_spring.common.CrudService;
import com.kolosov.learnjava_jc_spring.common.errors.exceptions.ResourceNotFoundException;
import com.kolosov.learnjava_jc_spring.jsonView.models.Order;
import com.kolosov.learnjava_jc_spring.jsonView.models.OrderProduct;
import com.kolosov.learnjava_jc_spring.jsonView.repositories.OrderProductRepository;
import com.kolosov.learnjava_jc_spring.jsonView.repositories.OrderRepository;
import com.kolosov.learnjava_jc_spring.jsonView.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService extends CrudService<Order, Long> {
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    public OrderService(UserService userService, OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        super(orderRepository);
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @Transactional
    public List<Order> getAll(Long userId) {
        if (!userService.existById(userId)) {
            throw new ResourceNotFoundException(String.format("User with id %d does not exist", userId));
        }
        List<Order> allByUserId = orderRepository.findAllByUserId(userId);
        return allByUserId;
    }

    @Transactional
    public Order save(Long userId, Order order) {
        User user = userService.getById(userId);
        if (user == null) {
            throw new IllegalArgumentException(String.format("Failed save Order entity. User with id %d does not exist", userId));
        }

        order.setUser(user);
        return this.save(order);
    }

    @Override
    public Order save(Order order) {
        if (order.getUser() == null) {
            throw new IllegalArgumentException("Failed save Order entity. User must not be null");
        }

        Order saved = super.save(order);

        if (order.getProducts() != null && !order.getProducts().isEmpty()) {
            for (OrderProduct product : order.getProducts()) {
                product.setOrder(order);
            }
        }

        return saved;
    }
}
