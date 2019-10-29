package com.hp.dao;

import com.hp.pojo.UserPower;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPowerDao {
    List<UserPower> selectUserPower(UserPower userPower);
}
