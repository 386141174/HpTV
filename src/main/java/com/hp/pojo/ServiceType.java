package com.hp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @Date 2023/2/25 17:47
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceType {

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    private Integer serviceType;

    private String serviceName;

    private Integer territoryId;

    private Date time;

    private String describeContent;

    private String communityName;
}
