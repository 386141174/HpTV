package com.hp.service;

import com.hp.pojo.Order;
import com.hp.pojo.Video;

import java.util.List;
import java.util.Set;

public interface OrderService {
    int insertOrder(Order order);
    boolean saveOrder();
    List<Order> selectOrder();
    int payOrder(Order order);
    void losePay();
    List<Order> queryOrderByUsername(String username);
    int deleteOrder(String orderid);
    List<Video> selectTeacherByUsername(String username);
    int ifBuy(String teacherName,String order_liveType,String userName);
    List<Order> queryStudentInfo(String teacher);
}
