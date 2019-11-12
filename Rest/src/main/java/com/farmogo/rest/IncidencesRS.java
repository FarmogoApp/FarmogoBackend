package com.farmogo.rest;

import com.farmogo.services.IncidencesService;
import com.farmono.model.Incidences;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@RequestScoped
@Path("incidence")
public class IncidencesRS {
    @Inject
    IncidencesService incidencesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Incidences> getAll() {
        return incidencesService.getAll();
    }
}