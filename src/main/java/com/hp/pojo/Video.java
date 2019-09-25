package com.hp.pojo;

/**
 *
 * 视频类
 */
public class Video {

    /**视频id*/
    private int id;
    /**视频本地名称*/
    private String name;
    /**视频访问路径*/
    private String url;
    /**本地磁盘路径*/
    private String lujing;
    /**视频类型*/
    private String filetype;
    /**视频上传名称*/
    private String videoname;

    public Video() {
    }


    public Video( String name, String url, String lujing, String filetype, String videoname) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.lujing = lujing;
        this.filetype = filetype;
        this.videoname = videoname;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLujing() {
        return lujing;
    }

    public void setLujing(String lujing) {
        this.lujing = lujing;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", lujing='" + lujing + '\'' +
                ", filetype='" + filetype + '\'' +
                ", videoname='" + videoname + '\'' +
                '}';
    }
}
