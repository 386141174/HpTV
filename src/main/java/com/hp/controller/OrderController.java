package com.hp.controller;

import com.hp.pojo.Order;
import com.hp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("order")
    public int a(){
        Order order = new Order();


        return orderService.insertOrder(order);
    }

    @RequestMapping("set")
    public void b(){
        String activity = "1";
        List<String> list = new ArrayList<>();
        for (int i = 0;i < 500;i++){
            list.add("1");
        }
        stringRedisTemplate.opsForList().leftPushAll(activity,list);

//        System.out.println(stringRedisTemplate.opsForList().size(activity));
    }
}
