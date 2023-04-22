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
 * @Date 2023/2/27 23:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEvaluate {

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    private String serviceNumber;

    private String evaluate;

    private Integer publishServiceId;

    private String image;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private String userName;
}
