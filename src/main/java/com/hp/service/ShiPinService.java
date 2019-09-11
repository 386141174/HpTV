package com.hp.service;

import com.hp.utils.Info;
import com.hp.utils.PageObject;

public interface ShiPinService {

   /**插入视频*/
    public int insertUrl(ShiPin shiPin);

    /**查询视频*/
    public PageObject<ShiPin> selectShipin(Info info);
}
