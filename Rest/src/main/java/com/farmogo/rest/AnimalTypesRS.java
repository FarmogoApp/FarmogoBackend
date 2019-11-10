package com.farmogo.rest;

import com.farmogo.services.AnimalTypesService;
import com.farmono.model.AnimalType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("animal-types")
public class AnimalTypesRS {

    // TODO: Return HTTP responses

    @Inject
    private AnimalTypesService animalTypesService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AnimalType> getAll() {
        return animalTypesService.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{animalType}")
    public AnimalType getAnimalType(@PathParam("animalType") int animalType) {
        return animalTypesService.getAnimalTypeByAnimalType(animalType);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAnimalType(AnimalType animalType) {
        animalTypesService.addAnimalType(animalType);
    }

    @DELETE
    @Path("/{animalType}")
    public void removeAnimalType(@PathParam("animalType") int animalType) {
        animalTypesService.removeAnimalType(animalType);
    }

    @PUT
    @Path("/{animalType}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAnimalType(@PathParam("animalType") int animalType, AnimalType updatedAnimalType){
        animalTypesService.updateAnimalType(animalType, updatedAnimalType);
    }
}
