package com.farmono.model;

import java.io.Serializable;

public class GrantAcces implements Serializable {
    private String userId;
    private  String farmId;
    private int role;
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
}
