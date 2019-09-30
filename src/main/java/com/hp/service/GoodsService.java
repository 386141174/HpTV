package com.hp.service;

import com.hp.pojo.Goods;
import org.springframework.web.multipart.MultipartFile;

public interface GoodsService {

    int createGoods(String courseName, MultipartFile image,int quantity,String endtime,String username);
    Goods selectGoods(String username);

}
