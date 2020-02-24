package com.hp.dao;

import com.hp.pojo.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
@Repository
public interface LoginMapper {
    UserLogin Login(@Param("username") String username);
    int selectbyUsername(@Param("username") String username);
    int addUser(UserLogin userLogin);
    boolean updateRemarkByUsername(@Param("remark") String remark, @Param("username") String username);

    int createUser(UserLogin userLogin);
    String selectUserToken(UserLogin userLogin);

}
