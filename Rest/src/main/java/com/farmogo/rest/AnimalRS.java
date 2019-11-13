package com.farmogo.rest;


import com.farmogo.services.AnimalService;
import com.farmogo.model.Animal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("animal")
public class AnimalRS {
    @Inject
    AnimalService animalService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> getAll() {
        return animalService.getAll();
    }
}
