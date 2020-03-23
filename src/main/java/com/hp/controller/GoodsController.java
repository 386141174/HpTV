package com.hp.controller;

//import ch.qos.logback.core.util.FileUtil;

import com.hp.dao.GoodsDao;
import com.hp.pojo.Goods;
import com.hp.service.GoodsService;
import com.hp.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;


@RequestMapping("goods")
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    @Autowired
    private GoodsDao goodsDao;

    /**
     * 创建活动商品
     * @param courseName
     * @param image
     * @param endtime
     * @param quantity
     * @param username
     * @return
     */
    @PostMapping("createGoods")
    public JsonResult createGoods(@RequestParam("courseName") String courseName,
                                  @RequestParam("image")MultipartFile image,
                                  @RequestParam("endtime") String endtime,
                                  @RequestParam("quantity") int quantity,
                                  @RequestParam("username") String username,
                                  @RequestParam("amount") BigDecimal amount,
                                  @RequestParam("liveType") String liveType){
        int count = goodsService.createGoods(courseName,image,quantity,endtime,username,amount,liveType);
        return count == 1? new JsonResult(1,"success") : new JsonResult(0,"error");
    }


    /**
     * 展示活动商品
     * @param
     * @return
     */
    @GetMapping("showGoods")
    public JsonResult showGoods(){
        Goods goods = goodsService.selectGoods();
       return goods != null ? new JsonResult(1,goods):new JsonResult(2,goods);
    }






}
