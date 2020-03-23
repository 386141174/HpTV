package com.hp.service;

import com.hp.pojo.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public interface GoodsService {

    int createGoods(String courseName,
                    MultipartFile image,
                    int quantity,
                    String endtime,
                    String username,
                    BigDecimal amount,
                    String liveType);
    Goods selectGoods();

}
