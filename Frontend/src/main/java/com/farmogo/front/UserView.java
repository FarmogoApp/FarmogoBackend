package com.farmogo.front;

import com.farmogo.model.User;
import com.farmogo.services.GlobalSessionService;
import com.farmogo.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class UserView implements Serializable {

    @Inject
    GlobalSessionService globalSessionService;

    @Inject
    UserService userService;

    User user;

    String email;
    String password;

    @PostConstruct
    public void init() throws IOException {
        user = globalSessionService.getUser();
        if (user == null) {

            FacesContext context = FacesContext.getCurrentInstance();

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String url = request.getRequestURL().toString();
            System.out.println(url);
            if (!url.contains("index.xhtml")) {
                context.getExternalContext().redirect("index.xhtml");
            }
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        user = userService.getByEmail(email);
        if (user !=null){
            globalSessionService.setUser(user);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", user.getName());
            context.addMessage(null,message);
            context.getExternalContext().redirect("home.xhtml");
            context.getExternalContext().getFlash().setKeepMessages(true);
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "");
            context.addMessage(null,message);
        }
    }

    public void logout() throws IOException {
        globalSessionService.setUser(null);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("index.xhtml");
    }
}
