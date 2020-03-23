package com.hp.service.imp;

import com.alibaba.fastjson.JSON;
import com.hp.dao.OrderDao;
import com.hp.dao.VideoDao;
import com.hp.pojo.Order;
import com.hp.pojo.Video;
import com.hp.service.OrderService;
import com.hp.utils.Info;
import com.hp.vo.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private VideoDao videoDao;
    @Autowired
    private RedisKey redisKey;


    /**
     * 活动开始存入redis进行订单保存
     * @param order
     * @return
     */
    @Override
    public int insertOrder(Order order) {
        Long count = Long.valueOf(0);
        String Str1= UUID.randomUUID().toString().replace("-", "");
        int roomNumber = orderDao.queryRoom(order.getTeacher());
        order.setOrderId(Str1);
        order.setOrder_roomNumber(roomNumber);
        String jsonString2 = JSON.toJSONString(order);
        count=redisTemplate.opsForList().leftPush(redisKey.ORDER_KEY,jsonString2);
        return count != null ?1:0;
    }

    /**
     * 活动结束存入数据库
     * @return
     */
    @Override
    public boolean saveOrder() {
        boolean ifExist =  redisTemplate.hasKey(redisKey.ORDER_KEY);
        if (ifExist){
            List<String> orders = redisTemplate.opsForList().range(redisKey.ORDER_KEY, 0, -1);
            List<Order> orderList = new ArrayList<>();
            if (orders != null){
                for (String order:orders
                ) {

                    orderList.add(JSON.parseObject(order, Order.class));
                }
            }
            int count = orderDao.saveOrder(orderList);
            redisTemplate.delete(redisKey.ORDER_KEY);
            deleteKey();
            return count == 1;
        }else {
            return deleteKey();

        }
    }

    public boolean deleteKey() {
        if (redisTemplate.hasKey(redisKey.GOODS_KEY)){
            redisTemplate.delete(redisKey.GOODS_KEY);
        }
        orderDao.deleteGoods();
        return redisTemplate.hasKey(redisKey.ORDER_KEY);
    }

    @Override
    public List<Order> selectOrder() {
        List<Order> orders = orderDao.selectOrder();
        redisTemplate.opsForValue().get(RedisKey.ORDER_KEY);
        return orders;
    }


    @Override
    public int payOrder(Order order) {
        String Str1= UUID.randomUUID().toString().replace("-", "");
        System.out.println(Str1.length());
        Date date = new Date();
        order.setOrderId(Str1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setCreateTime(dateFormat.format(date));
        return orderDao.payOrder(order);
    }

    @Override
    public void losePay() {
        Long count = redisTemplate.opsForList().leftPush(RedisKey.GOODS_KEY,"good");
//        System.out.println(count);
    }


    @Override
    public List<Order> queryOrderByUsername(String username) {
        return orderDao.queryOrderByUsername(username);
    }

    @Override
    public int deleteOrder(String orderid) {
        return orderDao.deleteOrder(orderid);
    }


    @Override
    public List<Video> selectTeacherByUsername(String username) {
        Set<String> teacher = orderDao.selectTeacherByUsername(username);
        if (teacher.size() != 0) {
            Set<String> videoType = orderDao.selectVideoType(teacher);
            List<Video> videos = orderDao.recommendVideo(videoType);
            return videos;
        }else {
            Info info = new Info();
            info.setSize(10);
            info.setStartRow(0);

            return videoDao.selAllOrders(info);
        }

//        System.out.println(teacher.toString());
//        System.out.println("---------------" + videoType.toString());
//        System.out.println(videos.toString());

    }


    @Override
    public int ifBuy(String teacherName, String order_liveType,String userName) {
        return orderDao.ifBuy(teacherName,order_liveType,userName);
    }


    @Override
    public List<Order> queryStudentInfo(String teacher) {

        return orderDao.queryStudentInfo(teacher);
    }
}
