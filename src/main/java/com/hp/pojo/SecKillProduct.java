package com.hp.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SecKillProduct
 * @Description: 秒杀商品实体类
 * @Author 小猴同学
 * @Date 2019/9/13 19:17
 * @Version 1.0
 **/
@Data
public class SecKillProduct implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 关联商品id
     */
    private Long productId;

    /**
     * 标题
     */
    private String title;

    /**
     * 商品图片
     */
    private String productPic;

    /**
     * 商品库存
     */
    private Integer stock;

    /**
     * 讲师
     */
    private String teacherName;

    /**
     * 秒杀开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;


}
