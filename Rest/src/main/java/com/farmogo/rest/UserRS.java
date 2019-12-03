package com.farmogo.rest;

import com.farmogo.services.UserService;
import com.farmogo.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRS {

    @Inject
    UserService userService;

    @GET
    public List<User> getAll() {
        return userService.getAll();
    }


    @POST
    public User create(User user){
        return  userService.create(user);
    }
}
