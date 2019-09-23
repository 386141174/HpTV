package com.hp.dao;

import com.hp.pojo.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper {
    UserLogin Login(@Param("username") String username);
    int selectbyUsername(@Param("username") String username);
    boolean addUser(@Param("username") String username, @Param("password") String password);
    boolean updateRemarkByUsername(@Param("remark") String remark, @Param("username") String username);


}
