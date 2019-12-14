package com.farmogo.rest;

import com.farmogo.model.User;
import com.farmogo.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
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
        if ("GET".equals(containerRequestContext.getMethod()) && containerRequestContext.getUriInfo().getPath().startsWith("/users/firebase"))
            return;

        String authorization = containerRequestContext.getHeaderString("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) throw new ForbiddenException();

        // TODO: use real verification to firebase
        User user = userService.getByFirebaseUuid(authorization.substring(7));
        if (user == null) {
            throw new NotAuthorizedException("You must be logged to get data");
        }
        userService.setCurrentUser(user);

    }
}
