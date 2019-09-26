package com.hp.dao;

import com.hp.pojo.Order;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDao {
    int insertOrder(Order order);
}
