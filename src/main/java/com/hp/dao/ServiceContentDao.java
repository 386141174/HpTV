package com.hp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.pojo.ServiceContent;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/27 13:13
 **/
@Repository
public interface ServiceContentDao extends BaseMapper<ServiceContent> {

    Page<ServiceContent> pageSearch(@Param("page") IPage page, @Param("search") String search);

    List<ServiceContent> serviceByArea(@Param("area") Integer id);
}
