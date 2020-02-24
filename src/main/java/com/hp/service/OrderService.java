package com.hp.service;

import com.hp.pojo.Order;

import java.util.List;

public interface OrderService {
    int insertOrder(Order order);
    boolean saveOrder();
    List<Order> selectOrder();
}
