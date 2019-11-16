package com.farmogo.services;

import com.farmogo.model.Farm;
import com.farmogo.model.User;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class GlobalSessionService implements Serializable {

    private User user;
    private Farm farm;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}
