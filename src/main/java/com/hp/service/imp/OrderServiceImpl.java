package com.hp.service.imp;

import com.hp.dao.OrderDao;
import com.hp.pojo.Order;
import com.hp.service.OrderService;
import com.hp.vo.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderDao orderDao;
    private RedisKey redisKey;


    /**
     * 活动开始存入redis进行订单保存
     * @param order
     * @return
     */
    @Override
    public int insertOrder(Order order) {
        if (redisTemplate.opsForList().leftPop("1") != null){
            String Str1= UUID.randomUUID().toString().replace("-", "");
            order.setOrderId(Str1);
            redisTemplate.opsForList().leftPush(redisKey.ORDER_KEY,order);
        }
        return 1;

    }

    /**
     * 活动结束存入数据库
     * @return
     */
    @Override
    public boolean saveOrder() {
        boolean ifExist =  redisTemplate.hasKey(redisKey.ORDER_KEY);


        if (ifExist){
            List<Order> orders = redisTemplate.opsForList().range(redisKey.ORDER_KEY,0,-1);
            int count = orderDao.saveOrder(orders);
            return count == 1;
        }else {
            return false;

        }
    }
}
