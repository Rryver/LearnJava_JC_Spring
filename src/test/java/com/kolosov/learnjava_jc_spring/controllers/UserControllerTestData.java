package com.kolosov.learnjava_jc_spring.controllers;

import com.kolosov.learnjava_jc_spring.jsonView.models.Order;
import com.kolosov.learnjava_jc_spring.jsonView.models.OrderProduct;
import com.kolosov.learnjava_jc_spring.jsonView.models.OrderStatus;
import com.kolosov.learnjava_jc_spring.jsonView.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserControllerTestData {
    public static User user1WithoutOrders   = new User(1L, "user1", "user1@example.ru");
    public static User user1WithOrders      = new User(1L, "user1", "user1@example.ru");

    public static User user2WithoutOrders   = new User(2L, "user2", "user2@example.ru");
    public static User user2WithOrders      = new User(2L, "user2", "user2@example.ru");

    public static User newUser = new User(null, "name", "user@example.ru");
    public static User invalidUser = new User(null, "name", "email");

    private static Order order1;
    private static Order order2;
    private static Order order3;

    private static OrderProduct orderProduct1;
    private static OrderProduct orderProduct2;
    private static OrderProduct orderProduct3;
    private static OrderProduct orderProduct4;

    static {
        order1 = new Order(1L, user1WithOrders, OrderStatus.CREATED);
        orderProduct1 = new OrderProduct(1L, "Apple", 1.0, 1);
        orderProduct2 = new OrderProduct(2L, "Pen", 2.0, 1);
        ArrayList<OrderProduct> products1 = new ArrayList<>() {{
            add(orderProduct1);
            add(orderProduct2);
        }};
        order1.setProducts(products1);

        order2 = new Order(2L, user1WithOrders, OrderStatus.IN_PROGRESS);
        orderProduct3 = new OrderProduct(3L, "Potato", 1.0, 4);
        orderProduct4 = new OrderProduct(4L, "Lemon", 2.0, 1);
        ArrayList<OrderProduct> products2 = new ArrayList<>() {{
            add(orderProduct3);
            add(orderProduct4);
        }};
        order2.setProducts(products2);

        order3 = new Order(3L, user2WithOrders, OrderStatus.COMPLETED);
        order3.setProducts(List.of(orderProduct1));

        user1WithOrders.setOrders(List.of(order1, order2));
        user2WithOrders.setOrders(List.of(order3));
    }
}
