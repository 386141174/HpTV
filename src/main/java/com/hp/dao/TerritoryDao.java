package com.hp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.pojo.PublishService;
import com.hp.pojo.Territory;
import org.springframework.data.repository.query.Param;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/25 13:11
 **/
public interface TerritoryDao extends BaseMapper<Territory> {

    Page<Territory> pageSearch(@Param("page") IPage page, @Param("search") String search);

    int selectAreaNameCount(@Param("areaName") String areaName);
}
