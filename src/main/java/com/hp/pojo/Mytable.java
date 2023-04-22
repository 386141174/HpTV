package com.hp.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/23 14:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mytable {

    private int id;

    private String menuname;

    private Integer parentid;

    private Integer orderid;

    private String menuurl;

    private String menuicon;

    private String isenabled;
}
