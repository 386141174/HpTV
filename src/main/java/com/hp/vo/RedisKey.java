package com.hp.vo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class RedisKey {

    //创建订单时redis的key
    public static String ORDER_KEY = "rush to purchase";
    //创建活动时
    public static String GOODS_KEY = "Start of activity";
}
