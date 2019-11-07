package com.hp.service.imp;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.hp.dao.GoodsDao;
import com.hp.pojo.Goods;
import com.hp.service.GoodsService;
import com.hp.vo.RedisKey;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
public class GoodsServiceImpl implements GoodsService {


    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private LiveServiceImp liveServiceImp;

    @Autowired
    private RedisTemplate redisTemplate;




    /**'
     * 创建活动
     * @param courseName 课程名称
     * @param image 课程描述
     * @param quantity 课程名额
     * @param endtime 结束时间
     * @return
     */
    @Override
    public int createGoods(String courseName, MultipartFile image, int quantity, String endtime,String username) {
        QrCodeUtil.generate(//
                "http://hutool.cn/", //二维码内容
                QrConfig.create().setImg("../../../../static/503d269759ee3d6dbf598fca4e166d224f4ade10.png"), //附带logo
                FileUtil.file("/file/qrcodeWithLogo1.jpg")//写出到的文件
        );
        String imageid = liveServiceImp.saveImage(image);
        Goods goods = new Goods();
        goods.setCourseName(courseName);
        goods.setEndtime(endtime);
        goods.setImage(imageid);
        goods.setQuantity(quantity);
        goods.setUsername(username);
        int count = goodsDao.createGoods(goods);
        return count;
    }




    /**
     * 展示活动商品
     * @param
     * @return
     */
    @Override
    public Goods selectGoods() {
        RedisKey redisKey = new RedisKey();
        Goods goods = goodsDao.showGoods();
        System.out.println(redisTemplate.opsForList().size(redisKey.ORDER_KEY));
        if (redisTemplate.hasKey(redisKey.ORDER_KEY) != null && goods != null){
            goods.setSurplus(redisTemplate.opsForList().size(redisKey.ORDER_KEY));
            return goods;
        }else {
            return goods;
        }
    }
}
