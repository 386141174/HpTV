package com.hp.pojo;

/**
 *
 * 视频类
 */
public class ShiPin {

    /**视频id*/
    private int id;
    /**视频名称*/
    private String name;
    /**视频访问路径*/
    private String url;
    /**本地磁盘路径*/
    private String lujing;
    /**视频类型*/
    private String filetype;

    public ShiPin() {
    }

    public ShiPin(String name, String url, String lujing, String filetype) {
        this.name = name;
        this.url = url;
        this.lujing = lujing;
        this.filetype = filetype;
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

    public String getType() {
        return filetype;
    }

    public void setType(String type) {
        this.filetype = type;
    }

    @Override
    public String toString() {
        return "ShiPin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", lujing='" + lujing + '\'' +
                ", type='" + filetype + '\'' +
                '}';
    }
}
