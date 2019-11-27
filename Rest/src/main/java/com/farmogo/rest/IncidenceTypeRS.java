package com.farmogo.rest;


import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("incidenceType")
public class IncidenceTypeRS {
    //@Inject
    //IncidenceTypeService incidenceTypeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List getAll() {
        return null;
    }
}