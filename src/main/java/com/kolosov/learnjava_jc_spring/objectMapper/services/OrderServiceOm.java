package com.kolosov.learnjava_jc_spring.objectMapper.services;

import com.kolosov.learnjava_jc_spring.common.CrudService;
import com.kolosov.learnjava_jc_spring.common.errors.exceptions.ResourceNotFoundException;
import com.kolosov.learnjava_jc_spring.objectMapper.dto.OrderForm;
import com.kolosov.learnjava_jc_spring.objectMapper.dto.OrderProductsDto;
import com.kolosov.learnjava_jc_spring.objectMapper.models.*;
import com.kolosov.learnjava_jc_spring.objectMapper.repository.OrderRepositoryOm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceOm extends CrudService<OrderOm, Long> {
    private final CustomerServiceOm customerService;
    private final ProductServiceOm productService;

    public OrderServiceOm(OrderRepositoryOm orderRepository, CustomerServiceOm customerService, ProductServiceOm productService) {
        super(orderRepository);
        this.customerService = customerService;
        this.productService = productService;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public OrderOm save(OrderForm orderForm) {
        CustomerOm customer = customerService.getById(orderForm.getCustomerId());
        if (customer == null) {
            throw new ResourceNotFoundException("Error creating Order. Customer does not exist");
        }

        OrderOm orderOm = new OrderOm();
        orderOm.setCustomer(customer);
        orderOm.setOrderStatus(OrderStatusOm.CREATED);

        List<OrderProductOm> productOms = new ArrayList<>();
        orderForm.getOrderProducts().forEach(product -> {
            addNewProductToOrder(product, orderOm);
        });
        orderOm.setOrderProducts(productOms);

        return super.save(orderOm);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public OrderOm update(Long id, OrderForm newOrder) {
        OrderOm oldOrder = getById(id);
        if (oldOrder == null) {
            throw new ResourceNotFoundException(String.format("Order with id %d does not exist", id));
        }

        if (newOrder.getOrderStatus() != null) {
            oldOrder.setOrderStatus(newOrder.getOrderStatus());
        }

        updateOrderProducts(newOrder, oldOrder);
        oldOrder.setTotalPrice(oldOrder.calculateTotalPrice());

        return super.update(id, oldOrder);
    }

    private void updateOrderProducts(OrderForm orderForm, OrderOm oldOrder) {
        List<OrderProductsDto> newOrderProducts = orderForm.getOrderProducts();
        List<OrderProductOm> oldOrderProducts = oldOrder.getOrderProducts();

        deleteDeleted(newOrderProducts, oldOrderProducts);

        Map<Long, OrderProductOm> oldOrderProductsMap = oldOrderProducts.stream()
                .collect(Collectors.toMap(entry -> entry.getProduct().getId(), entry -> entry));

        for (OrderProductsDto newOrderProduct : newOrderProducts) {
            if (newOrderProduct.getId() != null) {
                OrderProductOm oldOrderProductOm = oldOrderProductsMap.get(newOrderProduct.getProductId());
                changeCountInOldOrder(newOrderProduct, oldOrderProductOm);
            } else {
                addNewProductToOrder(newOrderProduct, oldOrder);
            }
        }
    }

    private void addNewProductToOrder(OrderProductsDto newOrderProduct, OrderOm oldOrder) {
        ProductOm productOm = productService.decreaseQuantityStock(newOrderProduct.getProductId(), newOrderProduct.getCount());
        OrderProductOm newOrderProductOm = createNewOrderProductOm(oldOrder, productOm, newOrderProduct.getCount());
        oldOrder.getOrderProducts().add(newOrderProductOm);
    }

    private void changeCountInOldOrder(OrderProductsDto newOrderProduct, OrderProductOm oldOrderProductOm) {
        Integer oldCount = oldOrderProductOm.getCount();
        Integer newCount = newOrderProduct.getCount();
        if (oldCount > newCount) {
            productService.increaseQuantityStock(newOrderProduct.getProductId(), oldCount - newCount);
            oldOrderProductOm.setCount(newOrderProduct.getCount());
        } else if (oldCount < newCount) {
            productService.decreaseQuantityStock(newOrderProduct.getProductId(), newCount - oldCount);
            oldOrderProductOm.setCount(newOrderProduct.getCount());
        }
    }

    private void deleteDeleted(List<OrderProductsDto> newOrderProducts, List<OrderProductOm> oldOrderProducts) {
        List<Long> orderProductDtoIds = newOrderProducts.stream()
                .map(OrderProductsDto::getProductId)
                .toList();
        Iterator<OrderProductOm> iterator = oldOrderProducts.iterator();
        while (iterator.hasNext()) {
            OrderProductOm orderProduct = iterator.next();
            Long productId = orderProduct.getProduct().getId();
            if (!orderProductDtoIds.contains(productId)) {
                iterator.remove();
                productService.increaseQuantityStock(productId, orderProduct.getCount());
            }
        }
    }

    private OrderProductOm createNewOrderProductOm(OrderOm order, ProductOm product, int count) {
        OrderProductOm orderProductOm = new OrderProductOm();
        orderProductOm.setOrder(order);
        orderProductOm.setProduct(product);
        orderProductOm.setProductName(product.getName());
        orderProductOm.setPrice(product.getPrice());
        orderProductOm.setCount(count);

        return orderProductOm;
    }
}
