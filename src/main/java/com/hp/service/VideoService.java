package com.hp.service;

import com.hp.pojo.Video;
import com.hp.utils.Info;
import com.hp.utils.PageObject;

import java.util.List;

public interface VideoService {

   /**插入视频*/
    public int inserVideo(Video video);

    /**查询视频*/
    public PageObject<Video> selectVideo(Info info);

    PageObject<Video> queryVideo(Info info);
    List<Video> selectVideoType(String videoType);
    List<Video> queryTeacherVideo(String teachername);
    int deleteVideo(int videoId);
    List<Video> replay(Video video);
}
