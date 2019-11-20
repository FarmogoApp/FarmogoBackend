package com.farmogo.rest;


import com.farmogo.model.*;
import com.farmogo.services.AnimalService;
import com.farmogo.services.AnimalTypesService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@RequestScoped
@Path("animal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
    @Path("{id}")
    public Animal get(@PathParam("id") String id) {
        return animalService.get(id);
    }

    @POST
    public Animal save(Animal animal) {  return animalService.save(animal);}

    @DELETE
    @Path("{id}")
    public Animal delete(@PathParam("id") String id) {
        Animal animal = animalService.get(id);
        if(animal== null) throw new NotFoundException();
        animalService.delete(animal);
        return animal;
    }

    @GET
    @Path("test")
    public String test(){

        Animal animal = new Animal();
        AnimalType animalType = new AnimalType();

        animal.setOrigin("Lleida");
        animal.setSex("Female");

        animalType.setDescription("test");
        animalType.setIcon("icon");
        AnimalType animalTypeId = animalTypesService.save(animalType);

        animal.setFarmId("5dd2da5155d353e9ef6df9eb");
        animal.setAnimalTypeId(animalTypeId.getAnimalType());
        animal.setRaceId("");
        animal.setMotherId("");
        animalService.save(animal);

        return "ok";
    }

}
