package com.hp.pojo;

public class ShiPin {

    /**视频*/
    private int id;
    /**视频名称*/
    private String name;
    /**视频访问路径*/
    private String url;

    public ShiPin() {
    }

    public ShiPin(String url, String name, String lujing) {
        this.name = name;
        this.url = url;
        this.lujing = lujing;
    }

    /**本地磁盘路径*/
    private String lujing;

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

    @Override
    public String toString() {
        return "ShiPin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", lujing='" + lujing + '\'' +
                '}';
    }
}
