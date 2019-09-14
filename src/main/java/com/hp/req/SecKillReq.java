package com.hp.req;

import lombok.Data;

/**
 * @ClassName SecKillReq
 * @Description: 秒杀请求实体类
 * @Author 小猴同学
 * @Date 2019/9/14 15:00
 * @Version 1.0
 **/
@Data
public class SecKillReq  {

    /**
     * 秒杀商品的id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

}
