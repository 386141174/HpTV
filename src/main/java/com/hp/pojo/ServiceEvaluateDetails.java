package com.hp.pojo;

import lombok.Data;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/27 23:57
 **/
@Data
public class ServiceEvaluateDetails extends PublishService{

    private String serviceNumber;

    private Integer serviceType;

    private String evaluate;

    private String image;
}
