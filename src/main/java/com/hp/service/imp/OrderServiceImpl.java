package com.hp.service.imp;

import com.hp.pojo.Order;
import com.hp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public int insertOrder(Order order) {
        if (stringRedisTemplate.opsForList().leftPop("1") != null){
            stringRedisTemplate.opsForList().leftPush("2","2");
        }
        return 1;

    }

    /**
     * 存入redis商品
     */
    public void setRedis(){
        String activity = "1";
        List<String> list = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            list.add("1");
        }
        stringRedisTemplate.opsForList().leftPushAll(activity,list);

    }

    /**
     * 判断redis是否存入商品
     * @param key
     * @return
     */
    public Boolean ifExist(String key){
        return stringRedisTemplate.hasKey(key);
    }
}
