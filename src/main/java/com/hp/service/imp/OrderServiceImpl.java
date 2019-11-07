package com.hp.service.imp;

import com.alibaba.fastjson.JSON;
import com.hp.dao.OrderDao;
import com.hp.pojo.Order;
import com.hp.service.OrderService;
import com.hp.vo.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RedisKey redisKey;


    /**
     * 活动开始存入redis进行订单保存
     * @param order
     * @return
     */
    @Override
    public int insertOrder(Order order) {
        Long count = Long.valueOf(0);
        String Str1= UUID.randomUUID().toString().replace("-", "");
        order.setOrderId(Str1);
        String jsonString2 = JSON.toJSONString(order);
        count=redisTemplate.opsForList().leftPush(redisKey.ORDER_KEY,jsonString2);
        return count != null ?1:0;
    }

    /**
     * 活动结束存入数据库
     * @return
     */
    @Override
    public boolean saveOrder() {
        boolean ifExist =  redisTemplate.hasKey(redisKey.ORDER_KEY);
        if (ifExist){
            List<String> orders = redisTemplate.opsForList().range(redisKey.ORDER_KEY, 0, -1);
            List<Order> orderList = new ArrayList<>();
            if (orders != null){
                for (String order:orders
                ) {
                    orderList.add(JSON.parseObject(order, Order.class));
                }
            }
            int count = orderDao.saveOrder(orderList);
            redisTemplate.delete(redisKey.ORDER_KEY);
            if (redisTemplate.hasKey(redisKey.GOODS_KEY)){
                redisTemplate.delete(redisKey.GOODS_KEY);
            }
            orderDao.deleteGoods();
            return count == 1;
        }else {
            return false;

        }
    }
}
