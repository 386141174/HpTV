package com.hp.dao;

import com.hp.pojo.ShiPin;
import com.hp.utils.Info;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiPinDao {

    /**插入视频*/
    public int insertUrl(ShiPin shiPin);


    /**总记录数*/
    public Integer selectShipinCount(Info info);

    /**结果集*/
    public List<ShiPin> selAllOrders(Info info);
}
