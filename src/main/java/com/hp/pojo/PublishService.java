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
 * @Date 2023/2/23 16:38
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishService {

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    private String userName;

    private String phone;

    //区域
    private Integer area;

    private String floorUnit;

    private String startTime;

    private String endTime;

    private String servicePersonnel;

    private String servicePersonnelId;

    private String image;

    private String serviceContent;

    private String userId;

    private String serviceNumber;

    private Integer serviceValue;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private Integer state;
}
