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
 * @Date 2023/2/25 13:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Territory {

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    private Integer  serialNumber;

    private String areaName;

    private String communityName;

    private String address;

    private Integer starScope;

    private Integer endScope;

    private String principal;

    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
}
