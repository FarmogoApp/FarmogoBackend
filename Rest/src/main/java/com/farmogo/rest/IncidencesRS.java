package com.farmogo.rest;

import com.farmogo.model.ActionNotPermitted;
import com.farmogo.model.PermissionError;
import com.farmogo.model.incidences.Incidence;
import com.farmogo.services.IncidencesService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Incidence save(Incidence incidence) {
        try {
            return incidencesService.save(incidence);
        }catch (ActionNotPermitted ex){
            throw new NotAcceptableException();
        } catch (PermissionError accessNotAllowed) {
            throw new ForbiddenException();
        }
    }

}