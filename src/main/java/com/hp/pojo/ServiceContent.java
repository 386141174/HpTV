package com.hp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/27 11:38
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceContent {

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    private String areaName;

    private Integer communityId;

    private String communityName;

    private String phone;

    private String principal;

    private String serviceName;

    private String serviceValue;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
}
