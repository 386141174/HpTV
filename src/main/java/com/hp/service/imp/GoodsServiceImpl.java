package com.hp.service.imp;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.hp.dao.GoodsDao;
import com.hp.pojo.Goods;
import com.hp.service.GoodsService;
import com.hp.vo.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@Service
public class GoodsServiceImpl implements GoodsService {


    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private LiveServiceImp liveServiceImp;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;




    /**'
     * 创建活动
     * @param courseName 课程名称
     * @param image 课程描述
     * @param quantity 课程名额
     * @param endtime 结束时间
     * @return
     */
    @Override
    public int createGoods(String courseName,
                           MultipartFile image,
                           int quantity,
                           String endtime,
                           String username,
                           BigDecimal amount,
                           String liveType) {
        RedisKey redisKey = new RedisKey();
        List<String> list = new ArrayList<>();
        String imageid = liveServiceImp.saveImage(image);
        Goods goods = new Goods();
        goods.setCourseName(courseName);
        goods.setEndtime(endtime);
        goods.setImage(imageid);
        goods.setQuantity(quantity);
        goods.setUsername(username);
        goods.setAmount(amount);
        goods.setLiveType(liveType);
        int count = goodsDao.createGoods(goods);
        if (count == 1){
            for (int i = 0;i < quantity;i++){
                list.add("good"+i);
            }
            stringRedisTemplate.opsForList().leftPushAll(redisKey.GOODS_KEY,list);
        }
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
        System.out.println(stringRedisTemplate.opsForList().size(redisKey.GOODS_KEY));
        if (stringRedisTemplate.hasKey(redisKey.GOODS_KEY) != null && goods != null){
            goods.setSurplus(stringRedisTemplate.opsForList().size(redisKey.GOODS_KEY));
            return goods;
        }else {
            return goods;
        }
    }
}
