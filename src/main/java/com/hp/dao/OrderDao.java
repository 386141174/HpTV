package com.hp.dao;

import com.hp.pojo.Order;
import com.hp.pojo.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface OrderDao {
    int saveOrder(List<Order> orders);
    int deleteGoods();
    List<Order> selectOrder();
    int payOrder(Order order);
    int queryRoom(@Param("username") String userName);
    List<Order> queryOrderByUsername(@Param("username") String username);
    int deleteOrder(@Param("orderid") String orederid);
    Set<String> selectTeacherByUsername(@Param("username") String username);
    Set<String> selectVideoType(@Param("set") Set<String> set);
    List<Video> recommendVideo(@Param("set") Set<String> set);
    int ifBuy(@Param("teacherName") String teacherName,@Param("order_liveType") String order_liveType,@Param("userName") String userName);
    List<Order> queryStudentInfo(@Param("teacher") String teacher);
}
