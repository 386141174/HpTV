package com.hp.dao;

import com.hp.pojo.Live;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveDao {

    int createRoom(Live live);
}
