package com.hp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hp.pojo.ServiceEvaluateDetails;
import com.hp.pojo.UserEvaluate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/27 23:53
 **/
@Repository
public interface UserEvaluateDao extends BaseMapper<UserEvaluate> {

    List<ServiceEvaluateDetails> getServiceEvaluate(@Param("userName") String userName);
}
