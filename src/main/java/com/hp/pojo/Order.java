package com.hp.pojo;


import java.io.Serializable;
import java.math.BigDecimal;

public class Order  implements Serializable {

    private String orderId;
    private String username;
    private String course;
    private String createTime;
    private BigDecimal order_amount;
    private String order_videoType;
    private String order_liveType;
    private int order_roomNumber;
    private String order_status;
    private String teacher;
    private UserLogin userLogin;

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public int getOrder_roomNumber() {
        return order_roomNumber;
    }

    public void setOrder_roomNumber(int order_roomNumber) {
        this.order_roomNumber = order_roomNumber;
    }

    public BigDecimal getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(BigDecimal order_amount) {
        this.order_amount = order_amount;
    }

    public String getOrder_videoType() {
        return order_videoType;
    }

    public void setOrder_videoType(String order_videoType) {
        this.order_videoType = order_videoType;
    }

    public String getOrder_liveType() {
        return order_liveType;
    }

    public void setOrder_liveType(String order_liveType) {
        this.order_liveType = order_liveType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", username='" + username + '\'' +
                ", course='" + course + '\'' +
                ", createTime='" + createTime + '\'' +
                ", order_amount=" + order_amount +
                ", order_videoType='" + order_videoType + '\'' +
                ", order_liveType='" + order_liveType + '\'' +
                ", order_roomNumber=" + order_roomNumber +
                '}';
    }
}
