package com.farmogo.rest;

import com.farmogo.services.RacesService;
import com.farmono.model.Races;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("races")
public class RacesRS {
    @Inject
    RacesService racesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Races> getAll() {
        return racesService.getAll();
    }
}