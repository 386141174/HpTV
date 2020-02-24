package com.hp.dao;

import com.hp.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDao {
    int saveOrder(List<Order> orders);
    int deleteGoods();
    List<Order> selectOrder();
}
