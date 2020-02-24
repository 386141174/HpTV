package com.hp.service;

import com.hp.pojo.UserLogin;
import com.hp.pojo.UserPower;

import java.util.List;

public interface LoginService {

    UserLogin Login(String username);
    int selectbyUsername(String username);
    int addUser(UserLogin userLogin);
    boolean updateRemarkByUsername(String remark, String username);
    String selectUserPower(UserPower userPower);
    int createUser(UserLogin userLogin);
    String selectUserToken(UserLogin userLogin);

}
