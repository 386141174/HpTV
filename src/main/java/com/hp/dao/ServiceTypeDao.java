package com.hp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.pojo.ServiceType;
import org.springframework.data.repository.query.Param;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/25 18:00
 **/
public interface ServiceTypeDao extends BaseMapper<ServiceType> {

    Page<ServiceType> pageSearch(@Param("page") IPage page, @Param("search") String search);

}
