package com.hp.pojo;

import lombok.Data;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/27 15:41
 **/
@Data
public class PublishServiceVo extends PublishService{

    private String servicePersonnel;

    private String serialName;

    private String timeScope;

    private String areaName;

    private String evaluate;

    private String serviceNumber;

    private Integer serviceType;

    private String image;
}
