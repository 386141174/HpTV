package com.hp.service.imp;

import com.hp.constants.CachePrefix;
import com.hp.pojo.SecKillProduct;
import com.hp.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SecKillServiceImpl
 * @Description: 秒杀service
 * @Author 小猴同学
 * @Date 2019/9/13 19:41
 * @Version 1.0
 **/
@Service
public class SecKillServiceImpl implements SecKillService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<SecKillProduct> querySecKillInfo() {
        List<SecKillProduct> secKillInfo = (List<SecKillProduct>) redisTemplate.boundValueOps(CachePrefix.SEC_KILL_LIST_INFO).get();
        if (secKillInfo == null){
            secKillInfo = new ArrayList<>();
        }
        return secKillInfo;
    }

    @Override
    public SecKillProduct querySecKillPro(Long id) {
        return (SecKillProduct) redisTemplate.boundValueOps(CachePrefix.SEC_KILL_PRO_INFO + id).get();
    }
}
