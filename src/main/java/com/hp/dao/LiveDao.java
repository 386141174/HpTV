package com.hp.dao;

import com.hp.pojo.Live;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;

@Repository
public interface LiveDao {

    int createRoom(Live live);

    Live selectRoom(@Param("username") String username);
}
