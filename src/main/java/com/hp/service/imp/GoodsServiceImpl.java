package com.hp.service.imp;

import com.hp.dao.GoodsDao;
import com.hp.pojo.Goods;
import com.hp.service.GoodsService;
import com.hp.vo.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


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
     * @param username 用户名
     * @return
     */
    @Override
    public Goods selectGoods(String username) {
        RedisKey redisKey = new RedisKey();
        Goods goods = goodsDao.showGoods(username);
        System.out.println(redisTemplate.opsForList().size(redisKey.ORDER_KEY));
        if (redisTemplate.hasKey(redisKey.ORDER_KEY) != null && goods != null){
            goods.setSurplus(redisTemplate.opsForList().size(redisKey.ORDER_KEY));
            return goods;
        }else {
            return goods;
        }
    }
}
