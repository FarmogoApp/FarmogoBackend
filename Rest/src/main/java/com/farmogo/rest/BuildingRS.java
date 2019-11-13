package com.farmogo.rest;

import com.farmogo.services.BuildingService;
import com.farmogo.model.Building;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@RequestScoped
@Path("building")
public class BuildingRS {
    @Inject
    BuildingService buildingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Building> getAll() {
        return buildingService.getAll();
    }
}