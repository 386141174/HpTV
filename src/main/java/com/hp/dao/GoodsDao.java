package com.hp.dao;

import com.hp.pojo.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDao {

    int createGoods(Goods goods);
    Goods showGoods(@Param("username") String username);
}
