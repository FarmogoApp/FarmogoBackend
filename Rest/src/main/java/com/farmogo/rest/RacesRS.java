package com.farmogo.rest;

import com.farmogo.model.Race;
import com.farmogo.services.RaceService;

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
    RaceService raceService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Race> getAll() {
        return raceService.getAll();
    }
}