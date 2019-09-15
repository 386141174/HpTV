package com.hp.dao;

import com.hp.pojo.Video;
import com.hp.utils.Info;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoDao {

    /**插入视频*/
    public int inserVideo(Video video);


    /**总记录数*/
    public Integer selectVideoCount(Info info);

    /**结果集*/
    public List<Video> selAllOrders(Info info);
}
