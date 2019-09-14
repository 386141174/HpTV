package com.hp.job;

import com.hp.constants.CachePrefix;
import com.hp.dao.SecKillDao;
import com.hp.pojo.SecKillProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SecKillTask
 * @Description: 秒杀定时任务
 * @Author 小猴同学
 * @Date 2019/9/14 10:04
 * @Version 1.0
 **/
@Component
@Configuration
@EnableScheduling
public class SecKillTask {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SecKillDao secKillDao;


    /**
     * 每隔五秒读取数据库,提前一天将秒杀课程加入到redis中缓存起来
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void pushSecKillPro(){
        Date now = new Date();
        //查询redis中秒杀的课程
        List<Long> ids = new ArrayList<>();
        List<SecKillProduct> pros = (List<SecKillProduct>) redisTemplate.boundValueOps(CachePrefix.SEC_KILL_LIST_INFO).get();
        if (pros == null){
            pros = new ArrayList<>();
        }else {
            //过滤掉已经超过结束时间失效的课程
            System.out.println("1------------>"+pros);
            pros = pros.stream().filter(v -> now.before(v.getEndTime())).collect(Collectors.toList());
            System.out.println("2------------>"+pros);
            pros.stream().forEach(v->ids.add(v.getId() ));
        }

        Date yesterday = getBeforeDay(now) ;
        //查询数据库中还未缓存的要开始的秒杀商品
        List<SecKillProduct> proList = secKillDao.querySecKillPro(ids,yesterday,now);
        if (proList!=null&&proList.size()>0){
            //需要将库存缓存到redis中去
            proList.stream().forEach(v->{
                pushGoodsList(v);
                //将没件秒杀商品信息都缓存到redis中去
                redisTemplate.boundValueOps(CachePrefix.SEC_KILL_PRO_INFO+v.getId()).set(v);
            });
            pros.addAll(proList);
        }
        redisTemplate.boundValueOps(CachePrefix.SEC_KILL_LIST_INFO).set(pros);

    }


    /**
     * 获取当前时间的前前一天的时间
     * @param date
     * @return
     */
    private static Date getBeforeDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     *缓存商品的库存信息
     * @param pro
     */
    private void pushGoodsList(SecKillProduct pro){
        for (int i = 0; i < pro.getStock(); i++) {
            redisTemplate.boundListOps(CachePrefix.SEC_KILL_PRO_STOCK+pro.getId()).leftPush(pro.getId());
        }
    }


}
