package com.leon.cloud.order.controller;

import com.leon.cloud.order.domain.Order;
import com.leon.cloud.order.domain.User;
import com.leon.cloud.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by leon on 2019/3/6.
 */

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/")
    public Order saveOrder(@Valid @RequestBody User user){
        return orderService.createOrder(user);
    }

    @GetMapping(path = "/{name}")
    public List<Order> getOrderByName(@PathVariable String name){
        return orderService.getOrderByName(name);
    }
}
