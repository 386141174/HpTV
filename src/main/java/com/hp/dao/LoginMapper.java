package com.hp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.pojo.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface LoginMapper extends BaseMapper<UserLogin> {
    UserLogin Login(@Param("username") String username);
    int selectbyUsername(@Param("username") String username);
    int addUser(UserLogin userLogin);
    boolean updateRemarkByUsername(@Param("remark") String remark, @Param("username") String username);

    int createUser(UserLogin userLogin);
    String selectUserToken(UserLogin userLogin);

    Page<UserLogin> getUsers(@Param("page") IPage page, @Param("search") String search);

    Page<UserLogin> getAttendant(@Param("page") IPage page, @Param("search") String search);

}
