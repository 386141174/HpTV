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
 * @Date 2023/2/26 11:47
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dictionary {

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    private String serialNumber;

    private Integer value;

    private Integer parentId;

    private String serialName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
}
