package com.farmogo.rest;

import com.farmogo.services.GrantAccesService;
import com.farmogo.model.GrantAcces;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("grantAcces")
public class GrantAccesRS {
    @Inject
    GrantAccesService grantAccesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GrantAcces> getAll() {
        return grantAccesService.getAll();
    }
}