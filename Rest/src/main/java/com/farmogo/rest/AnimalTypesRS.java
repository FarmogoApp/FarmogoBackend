package com.farmogo.rest;

import com.farmogo.services.AnimalTypesService;
import com.farmogo.model.AnimalType;
import com.sun.tracing.dtrace.ProviderAttributes;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("animalTypes")
public class AnimalTypesRS {
    @Inject
    AnimalTypesService animalTypesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AnimalType> getAll() {
        return animalTypesService.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AnimalType get(@PathParam("id") String id) {
        return animalTypesService.get(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(AnimalType animalType) { animalTypesService.save(animalType);}

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(AnimalType animalType) { animalTypesService.delete(animalType);}


}
