package com.farmogo.rest;

import com.farmogo.model.User;
import com.farmogo.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class FirebaseFilter implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if ("POST".equals(containerRequestContext.getMethod()) && "/users".equals(containerRequestContext.getUriInfo().getPath()))
            return;
        if ("GET".equals(containerRequestContext.getMethod()) && "/test/database".equals(containerRequestContext.getUriInfo().getPath()))
            return;

        String authorization = containerRequestContext.getHeaderString("Authorization");
        System.out.println("!!!!!!!!!!!!!!!AUTH rebuda" + authorization);

        if (authorization == null || !authorization.startsWith("Bearer ")) throw new ForbiddenException();

        User user = userService.getByFirebaseUuid(authorization.substring(7));
        if (user == null) {
            throw new ForbiddenException();
        }
        userService.setCurrentUser(user);

    }
}
