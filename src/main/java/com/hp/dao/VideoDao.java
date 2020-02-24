package com.hp.dao;

import com.hp.pojo.Video;
import com.hp.utils.Info;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoDao {

    /**插入视频*/
    int inserVideo(Video video);


    /**总记录数*/
    Integer selectVideoCount(Info info);

    /**结果集*/
    List<Video> selAllOrders(Info info);

    //搜索视频
    List<Video> queryVideo(Info info);
    int selectCount(@Param("videoname") String upVideoName);
}
