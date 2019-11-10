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
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("animal-types")
public class AnimalTypesRS {

    @Inject
    private AnimalTypesService animalTypesService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        return Response
                .status(Response.Status.OK)
                .entity(animalTypesService.getAll())
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{animalType}")
    public Response getAnimalType(@PathParam("animalType") int animalType) {

        // TODO: Find a better way?
        try{
            // animalType found
            AnimalType at = animalTypesService.getAnimalTypeByAnimalType(animalType);
            return Response.status(Response.Status.OK).entity(at).build();

        } catch (Exception e) {
            // animalType not found
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAnimalType(AnimalType animalType) {

        animalTypesService.addAnimalType(animalType);
        return Response.status(Response.Status.CREATED).entity(animalType).build();
    }

    @DELETE
    @Path("/{animalType}")
    public Response removeAnimalType(@PathParam("animalType") int animalType) {
        animalTypesService.removeAnimalType(animalType);
        return Response.status(Response.Status.OK).build();

    }

    @PUT
    @Path("/{animalType}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAnimalType(@PathParam("animalType") int animalType, AnimalType updatedAnimalType){

        // TODO: Find a better way?
        try{
            // animalType found
            animalTypesService.updateAnimalType(animalType, updatedAnimalType);
            return Response.status(Response.Status.OK).entity(updatedAnimalType).build();

        } catch (Exception e) {
            // animalType not found
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
