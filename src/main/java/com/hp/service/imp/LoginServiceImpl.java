package com.hp.service.imp;

import com.hp.dao.LoginMapper;
import com.hp.pojo.UserLogin;
import com.hp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Override
    public UserLogin Login(String username ) {
        return loginMapper.Login(username);
    }

    @Override
    public boolean updateRemarkByUsername(String remark, String username) {
        return loginMapper.updateRemarkByUsername(remark,username);
    }

    @Override
    public boolean addUser(UserLogin userLogin) {
        return loginMapper.addUser(userLogin);
    }

    @Override
    public int selectbyUsername(String username) {
        return loginMapper.selectbyUsername(username);
    }
}

