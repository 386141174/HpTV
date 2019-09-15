package com.hp.service;

import com.hp.pojo.Video;
import com.hp.utils.Info;
import com.hp.utils.PageObject;

public interface VideoService {

   /**插入视频*/
    public int inserVideo(Video video);

    /**查询视频*/
    public PageObject<Video> selectVideo(Info info);
}
