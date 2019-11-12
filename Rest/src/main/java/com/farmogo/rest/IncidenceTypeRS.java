package com.farmogo.rest;

import com.farmogo.services.IncidenceTypeService;
import com.farmono.model.IncidenceTypes;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("incidenceType")
public class IncidenceTypeRS {
    @Inject
    IncidenceTypeService incidenceTypeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<IncidenceTypes> getAll() {
        return incidenceTypeService.getAll();
    }
}