package com.hp.topic;

import com.hp.pojo.Order;
import com.hp.service.OrderService;

import org.apache.log4j.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
//public class Receiver {
//
//
//
//    @Autowired
//    private OrderService orderService;
//
//    Logger logger = Logger.getLogger(this.getClass());
//
//
//    @RabbitListener(queues = "active")
//    public void send(Order order) throws Exception{
//        logger.info("接收消息"+order);
//        logger.info("接收消息时间："+new Date());
//        orderService.insertOrder(order);
//    }
//
//
//
//}
