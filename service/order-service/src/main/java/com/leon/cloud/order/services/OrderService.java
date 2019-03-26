package com.leon.cloud.order.services;

import com.leon.cloud.order.domain.Order;
import com.leon.cloud.order.domain.User;

import java.util.List;

/**
 * Created by leon on 2019/3/6.
 */
public interface OrderService {
    Order createOrder(User user);

    List<Order> getOrderByName(String name);
}
