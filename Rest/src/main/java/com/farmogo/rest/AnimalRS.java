package com.farmogo.rest;


import com.farmogo.model.AccessNotAllowed;
import com.farmogo.model.Animal;
import com.farmogo.model.incidences.Incidence;
import com.farmogo.services.AnimalService;
import com.farmogo.services.IncidencesService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("animals")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AnimalRS {
    @Inject
    AnimalService animalService;

    @Inject
    IncidencesService incidencesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> getAll() {
        return animalService.getCurrentAnimals();
    }

    @GET
    @Path("{id}")
    public Animal get(@PathParam("id") String id) {
        try {
            Animal animal = animalService.get(id);
            if (animal == null) throw new NotFoundException();
            return animal;
        } catch (AccessNotAllowed accessNotAllowed) {
            throw new ForbiddenException();
        }
    }

    @GET
    @Path("{id}/incidences")
    public List<Incidence> getIncidences(@PathParam("id") String animalId,
                                         @QueryParam("skip") @DefaultValue("0") int skip,
                                         @QueryParam("limit") @DefaultValue("10") int limit ) {
        try {
            Animal animal = animalService.get(animalId);
            if (animal == null) throw new  NotFoundException();
            return incidencesService.getByAnimal(animalId, skip, limit);
        } catch (AccessNotAllowed accessNotAllowed) {
            throw new ForbiddenException();
        }
    }

    @POST
    public Animal save(Animal animal) {
        return animalService.save(animal);
    }

    @DELETE
    @Path("{id}")
    public Animal delete(@PathParam("id") String id) {
        try {
            Animal animal = animalService.get(id);
            if (animal == null) throw new NotFoundException();
            animalService.delete(animal);
            return animal;
        } catch (AccessNotAllowed accessNotAllowed) {
            throw new ForbiddenException();
        }
    }
}
