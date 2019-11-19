package com.farmogo.rest;


import com.farmogo.model.AnimalType;
import com.farmogo.services.AnimalService;
import com.farmogo.model.Animal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("animal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AnimalRS {
    @Inject
    AnimalService animalService;

    @GET
    public List<Animal> getAll() {
        return animalService.getAll();
    }


}
