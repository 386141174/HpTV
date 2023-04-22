package com.hp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hp.pojo.Goods;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDao extends BaseMapper<Goods> {

    int createGoods(Goods goods);
    Goods showGoods();

}
