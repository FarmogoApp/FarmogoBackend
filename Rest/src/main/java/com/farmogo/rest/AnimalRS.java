package com.farmogo.rest;


import com.farmogo.model.*;
import com.farmogo.services.AnimalService;
import com.farmogo.services.AnimalTypesService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@RequestScoped
@Path("animal")
public class AnimalRS {
    @Inject
    AnimalService animalService;
    @Inject
    AnimalTypesService animalTypesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> getAll() {
        return animalService.getAll();
    }


    @GET
    @Path("test")
    public String test(){

       /* Animal animal = new Animal();
        AnimalType animalType = new AnimalType();

        animal.setOrigin("Lleida");
        animal.setSex("Female");

        animalType.setDescription("test");
        animalType.setIcon("icon");
        AnimalType animalTypeId = animalTypesService.save(animalType);

        animal.setAnimalTypeId(animalTypeId.getAnimalType());
        animal.setRaceId("");
        animal.setMotherId("");
        animalService.save(animal);*/

        return "ok";
    }

}
