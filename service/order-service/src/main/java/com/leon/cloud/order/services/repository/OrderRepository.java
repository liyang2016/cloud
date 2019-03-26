package com.leon.cloud.order.services.repository;

import com.leon.cloud.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leon on 2019/3/6.
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order>,Serializable {
    List<Order> getOrderByUserName(String name);
}
