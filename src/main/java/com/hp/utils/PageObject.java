package com.hp.utils;

import java.util.List;

public class PageObject<T> {

    /**具体数据信息*/
    private List<T> items;
    /**总记录数*/
    private Integer total;
    /**总页数*/
    private Integer pageCount;
    /**当前页码*/
    private Integer pageCurrent;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    @Override
    public String toString() {
        return "PageObject{" +
                "items=" + items +
                ", total=" + total +
                ", pageCount=" + pageCount +
                ", pageCurrent=" + pageCurrent +
                '}';
    }
}

