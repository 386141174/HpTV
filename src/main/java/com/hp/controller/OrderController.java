package com.hp.controller;


import com.hp.pojo.Order;

import com.hp.service.OrderService;
import com.hp.utils.JsonResult;


import com.hp.vo.RedisKey;
import org.springframework.amqp.core.AmqpTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RedisKey redisKey;

    static int i = 1;

    @PostMapping("createOrder")
    public JsonResult createOrder(@RequestParam(value = "username",required = false) String username,
                                  @RequestParam(value = "courseName",required = false) String courseName){

        if (stringRedisTemplate.opsForList().leftPop(redisKey.GOODS_KEY) == null ){
            return new JsonResult(0,"商品已经卖完!");
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Order order = new Order();
        order.setUsername(username);
        order.setCourse(courseName);
        order.setCreateTime(dateFormat.format(date));
        amqpTemplate.convertAndSend("active",order);
        return new JsonResult(1,"success");
    }

    @GetMapping("activeFinsh")
    public void activeFinsh(){
        orderService.saveOrder();
    }

//    @RequestMapping("order")
//    public int a(){
//
//        Order order = new Order();
//        order.setCourse("如何从搬砖小哥到百万年薪的大牛");
//        order.setUsername("liu");
//        amqpTemplate.convertAndSend("active",order);
//        return 1;
//    }
//
//    @RequestMapping("set")
//    public void b(){
//        String activity = "1";
//        List<String> list = new ArrayList<>();
//        for (int i = 0;i < 500;i++){
//            list.add("1");
//        }
//        stringRedisTemplate.opsForList().leftPushAll("rush to purchase",list);
//    }
//
//    @RequestMapping("setsql")
//    public void setsql(){
//        orderService.saveOrder();
//    }


    @GetMapping("selectOrder")
    public JsonResult selectOrder(){
        return new JsonResult(1,orderService.selectOrder());
    }

}
