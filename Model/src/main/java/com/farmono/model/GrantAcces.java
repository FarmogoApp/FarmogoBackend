package com.farmono.model;

import java.io.Serializable;
import java.util.List;

public class GrantAcces implements Serializable {
    private String userId;
    private  String farmId;
    private int role;
    private List<User> userList;
    public GrantAcces(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
