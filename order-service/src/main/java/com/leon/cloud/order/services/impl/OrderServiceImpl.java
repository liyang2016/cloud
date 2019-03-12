package com.leon.cloud.order.services.impl;

import com.leon.cloud.order.client.AccountClient;
import com.leon.cloud.order.domain.Account;
import com.leon.cloud.order.domain.Order;
import com.leon.cloud.order.domain.User;
import com.leon.cloud.order.services.OrderService;
import com.leon.cloud.order.services.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * Created by leon on 2019/3/6.
 */

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private AccountClient accountClient;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(User user) {
        Order order = new Order();
        Account account = accountClient.getAccountByName(user.getUsername());
        if (account != null) {
            order.setUserId(account.getUserId());
            order.setUserName(account.getName());
            order.setCreateDate(new Date());
            order.setUpdateDate(new Date());
            order.setState(account.getState());
            orderRepository.saveAndFlush(order);
        } else {
            LOGGER.info("user no exists");
        }
        return order;
    }

    @Override
    public List<Order> getOrderByName(String name) {
        return orderRepository.getOrderByUserName(name);
    }
}
