package com.hp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hp.pojo.Dictionary;
import com.hp.pojo.DictionaryDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/26 11:55
 **/
@Repository
public interface DictionaryDao extends BaseMapper<Dictionary> {

    List<DictionaryDto> getAllDic();

    List<Dictionary> getDicByType(@Param("serialNumber") String serialNumber);
}
