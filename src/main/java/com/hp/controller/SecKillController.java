package com.hp.controller;

import com.hp.constants.CachePrefix;
import com.hp.pojo.SecKillProduct;
import com.hp.req.SecKillReq;
import com.hp.service.SecKillService;
import com.hp.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SecKillController
 * @Description: 秒杀Controller
 * @Author 小猴同学
 * @Date 2019/9/12 23:56
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/secKill")
public class SecKillController {

    @Autowired
    private SecKillService secKillService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询所有参与秒杀课程
     * @return
     */
    @RequestMapping(value = "/querySecKillListInfo",method = RequestMethod.GET)
    public JsonResult querySecKillListInfo(){
        List<SecKillProduct> pros = secKillService.querySecKillInfo();
        return new JsonResult(1,"查询成功!",pros);
    }


    /**
     * 查询具体秒杀课程
     * @return
     */
    @RequestMapping(value = "/querySecKillPro/{id}",method = RequestMethod.GET)
    public JsonResult querySecKillPro(@PathVariable("id") Long id){
        SecKillProduct pro = secKillService.querySecKillPro(id);
        return new JsonResult(1,"查询成功!",pro);
    }


    /**
     * 秒杀课程下单
     * @return
     */
    @RequestMapping(value = "/saveSecKill",method = RequestMethod.POST)
    public JsonResult saveSeKill(@RequestBody SecKillReq req){
        Long id = req.getId();
        if (id ==null){
            return new JsonResult(0,"课程不存在!");
        }
        //取出秒杀的商品信息
        SecKillProduct secKillProduct = (SecKillProduct) redisTemplate.boundValueOps(CachePrefix.SEC_KILL_PRO_INFO + id).get();
        if (secKillProduct==null){
            return new JsonResult(0,"课程不存在!");
        }
        Date now = new Date();
        if (now.before(secKillProduct.getStartTime())){
            return new JsonResult(0,"秒杀还未开始!");
        }
        if (now.after(secKillProduct.getEndTime())){
            return new JsonResult(0,"秒杀已经结束!");
        }
        Object o = redisTemplate.boundListOps(CachePrefix.SEC_KILL_PRO_STOCK + id).rightPop();
        if (o==null){
            return new JsonResult(0,"课程已抢完!");
        }
        //todo 订单保存

        return new JsonResult(1,"success");
    }
}
