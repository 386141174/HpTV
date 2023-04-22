package com.hp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.pojo.PublishService;
import com.hp.pojo.PublishServiceVo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/23 17:03
 **/
@Repository
public interface PublishServiceDao extends BaseMapper<PublishService> {

    Page<PublishServiceVo> pageSearch(@Param("page") IPage page, @Param("search") String search, @Param("userName") String userName,@Param("serviceName") String serviceName);

    List<PublishServiceVo> thisYearService(@Param("userName") String userName,@Param("serviceName") String serviceName,@Param("start") String start,@Param("end") String end);
}
