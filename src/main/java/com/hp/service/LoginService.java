package com.hp.service;

import com.hp.pojo.UserLogin;

public interface LoginService {

    public UserLogin Login(String username);
    int selectbyUsername(String username);
    boolean addUser(UserLogin userLogin);
    boolean updateRemarkByUsername(String remark, String username);

}
