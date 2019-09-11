package com.hp.utils;

/**
 * 获取页面信息类
 */
public class Info {
    /***视频类型*/
    private String filetype;
    /**每页条数*/
    private Integer size;
    /**当前页码*/
    private Integer pageCurrent;
    /**开始行*/
    private Integer startRow= 0;

    public Info() {
    }

    public Info(String filetype, Integer size, Integer pageCurrent, Integer startRow) {
        this.filetype = filetype;
        this.size = size;
        this.pageCurrent = pageCurrent;
        this.startRow = startRow;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    @Override
    public String toString() {
        return "Info{" +
                "filetype='" + filetype + '\'' +
                ", size=" + size +
                ", pageCurrent=" + pageCurrent +
                ", startRow=" + startRow +
                '}';
    }
}
