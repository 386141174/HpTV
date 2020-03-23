package com.hp.pojo;


import java.math.BigDecimal;
import java.util.Date;

public class Goods {

    private int id;
    private String courseName;
    private String image;
    private int quantity;
    private String endtime;
    private String username;
    private long surplus;
    private BigDecimal amount;
    private String liveType;


    public String getLiveType() {
        return liveType;
    }

    public void setLiveType(String liveType) {
        this.liveType = liveType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;


    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public long getSurplus() {
        return surplus;
    }

    public void setSurplus(long surplus) {
        this.surplus = surplus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
