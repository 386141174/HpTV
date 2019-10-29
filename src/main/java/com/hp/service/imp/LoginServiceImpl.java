package com.hp.service.imp;

import com.hp.dao.LoginMapper;
import com.hp.dao.UserPowerDao;
import com.hp.pojo.UserLogin;
import com.hp.pojo.UserPower;
import com.hp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private UserPowerDao userPowerDao;

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

    @Override
    public List<UserPower> selectUserPower(UserPower userPower) {
        return userPowerDao.selectUserPower(userPower);
    }
}

