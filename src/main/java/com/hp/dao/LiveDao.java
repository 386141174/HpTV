package com.hp.dao;

import com.hp.pojo.Live;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiveDao {

    int createRoom(Live live);
    Live selectRoom(@Param("username") String username);
    int updateRoom(Live live);
    List<Live> selectListRoom();
    Live getRtmp(@Param("roomNumber") int roonNumber);
    List<Live> selectLiveType(String username);
    int queryBuyLive(@Param("liveType") String liveType,@Param("userName") String userName);
    List<Live> queryStudentCourse(@Param("username") String username);
}
