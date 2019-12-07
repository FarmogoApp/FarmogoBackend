package com.farmogo.rest;

import com.farmogo.model.AccessNotAllowed;
import com.farmogo.model.incidences.*;
import com.farmogo.services.IncidencesService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;


@RequestScoped
@Path("incidences")
public class IncidencesRS {
    @Inject
    IncidencesService incidencesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Incidence> getAll() {
        return incidencesService.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(Incidence incidence) {
        try {
            incidencesService.save(incidence);
        } catch (AccessNotAllowed accessNotAllowed) {
            throw new ForbiddenException();
        }
    }

}