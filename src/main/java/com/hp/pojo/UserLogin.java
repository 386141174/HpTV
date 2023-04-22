package com.hp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "userlogin")
public class UserLogin implements Serializable {
    private static final long serialVersionUID=1L;

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    @TableField(value="username")
    private String username;
    @TableField(value="password")
    private String password;
    @TableField(value="token")
    private String token;
    @TableField(value="remark")
    private String remark;
    @TableField(value="realName")
    private String realName;
    @TableField(value="sex")
    private Integer sex;
    @TableField(value="idCard")
    private String idCard;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private Integer state;



    private String tel;
    private  String email;

}
