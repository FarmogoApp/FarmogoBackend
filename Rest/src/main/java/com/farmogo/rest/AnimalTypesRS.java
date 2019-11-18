package com.farmogo.rest;

import com.farmogo.services.AnimalTypesService;
import com.farmogo.model.AnimalType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    @Path("test")
    public String test(){
        AnimalType animalType = new AnimalType();
        animalType.setDescription("Cow");

        animalTypesService.save(animalType);

        animalType = new AnimalType();
        animalType.setDescription("Bull");

        animalTypesService.save(animalType);

        return "OK";
    }
}
