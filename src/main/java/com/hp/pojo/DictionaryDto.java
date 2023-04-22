package com.hp.pojo;

import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/26 12:53
 **/
@Data
public class DictionaryDto extends Dictionary{
    private List<DictionaryDto> children;
}
