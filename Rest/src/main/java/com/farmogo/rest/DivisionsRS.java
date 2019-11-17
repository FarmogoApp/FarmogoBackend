package com.farmogo.rest;

import com.farmogo.services.DivisionService;
import com.farmogo.model.Divisions;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("divisions")
public class DivisionsRS {
    @Inject
    DivisionService divisionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Divisions> getAll() {
        return divisionService.getAll();
    }
}