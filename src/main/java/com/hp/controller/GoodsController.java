package com.hp.controller;


import com.hp.pojo.Goods;
import com.hp.service.GoodsService;
import com.hp.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("goods")
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


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
                                  @RequestParam("username") String username){
        int count = goodsService.createGoods(courseName,image,quantity,endtime,username);

        return count == 1? new JsonResult(1,"success") : new JsonResult(0,"error");
    }


    /**
     * 展示活动商品
     * @param username
     * @return
     */
    @GetMapping("showGoods")
    public JsonResult showGoods(@RequestParam("username") String username){
        Goods goods = goodsService.selectGoods(username);
       return goods != null ? new JsonResult(1,goods):new JsonResult(2,goods);
    }
}
