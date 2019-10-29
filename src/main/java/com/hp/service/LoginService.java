package com.hp.service;

import com.hp.pojo.UserLogin;
import com.hp.pojo.UserPower;

import java.util.List;

public interface LoginService {

    public UserLogin Login(String username);
    int selectbyUsername(String username);
    boolean addUser(UserLogin userLogin);
    boolean updateRemarkByUsername(String remark, String username);

    List<UserPower> selectUserPower(UserPower userPower);

}
