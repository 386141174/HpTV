package com.hp.pojo;

import java.io.Serializable;

public class UserPower implements Serializable {
    private static final long serialVersionUID = 1628251423436596789L;
    private String roles;
    private String power;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
