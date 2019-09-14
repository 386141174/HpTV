package com.hp.service;

import com.hp.pojo.SecKillProduct;

import java.util.List;

/**
 * @ClassName SecKillService
 * @Description: 秒杀商品接口
 * @Author 小猴同学
 * @Date 2019/9/13 19:41
 * @Version 1.0
 **/
public interface SecKillService {

    /**
     * 查询秒杀课程
     * @return
     */
    List<SecKillProduct> querySecKillInfo();

    /**
     * 查询具体秒杀课程
     * @param id
     * @return
     */
    SecKillProduct querySecKillPro(Long id);
}
