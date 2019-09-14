package com.hp.dao;

import com.hp.pojo.SecKillProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SecKillDao
 * @Description: 秒杀商品mapper
 * @Author 小猴同学
 * @Date 2019/9/13 0:48
 * @Version 1.0
 **/
@Repository
public interface SecKillDao {

    /**
     * 查询即将开始的秒杀商品
     * @param ids
     * @param yesterday
     * @param now
     * @return
     */
    List<SecKillProduct> querySecKillPro(@Param("list") List<Long> ids, @Param("yesterday") Date yesterday, @Param("now") Date now);
}
