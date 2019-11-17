package com.farmogo.front;

import com.farmogo.model.User;
import com.farmogo.services.GlobalSessionService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class UserView implements Serializable {

    @Inject
    GlobalSessionService globalSessionService;

    User user;

    @PostConstruct
    public void init() {
        user = globalSessionService.getUser();
        if (user == null) throw new javax.faces.application.ProtectedViewException();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
