package com.hp.dao;

import com.hp.pojo.UserPower;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPowerDao {
    String selectUserPower(UserPower userPower);
}
