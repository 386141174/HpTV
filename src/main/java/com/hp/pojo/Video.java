package com.hp.pojo;

/**
 *
 * 视频类
 */
public class Video {

    /**视频id*/
    private int id;
    /**视频本地名称*/
    private String videoName;
    /**视频访问路径*/
    private String videoUrl;
    /**视频本地磁盘路径*/
    private String videoPath;
    /**图片本地名称*/
    private String picName;
    /**图片访问路径*/
    private String picUrl;
    /**图片本地磁盘路径*/
    private String picPath;
    /**视频类型*/
    private String filetype;
    /**视频上传名称*/
    private String upVideoName;
    //视频描述
    private String videodescribe;
    //用户名
    private String username;
    //视频内容分类
    private String videoType;
    //首页位置
    private int location;
    private String livetype;

    private int ifopen;

    public int getIfopen() {
        return ifopen;
    }

    public void setIfopen(int ifopen) {
        this.ifopen = ifopen;
    }

    public String getLivetype() {
        return livetype;
    }

    public void setLivetype(String livetype) {
        this.livetype = livetype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getDescribe() {
        return videodescribe;
    }

    public void setDescribe(String describe) {
        this.videodescribe = describe;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public Video() {
    }

    public Video(String videoName, String videoUrl, String videoPath,
                 String picName, String picUrl, String picPath,
                 String filetype, String upVideoName,String username,String liveType,
                 int open) {
        this.videoName = videoName;
        this.videoUrl = videoUrl;
        this.videoPath = videoPath;
        this.picName = picName;
        this.picUrl = picUrl;
        this.picPath = picPath;
        this.filetype = filetype;
        this.upVideoName = upVideoName;
        this.username = username;
        this.livetype = liveType;
        this.ifopen = open;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getUpVideoName() {
        return upVideoName;
    }

    public void setUpVideoName(String upVideoName) {
        this.upVideoName = upVideoName;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", videoName='" + videoName + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", picName='" + picName + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", picPath='" + picPath + '\'' +
                ", filetype='" + filetype + '\'' +
                ", upVideoName='" + upVideoName + '\'' +
                '}';
    }
}
