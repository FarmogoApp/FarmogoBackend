package com.farmogo.rest;

import com.farmogo.services.AnimalTypesService;
import com.farmogo.model.AnimalType;
import com.farmogo.services.HasRelationatedDataException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("animalTypes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AnimalTypesRS {
    @Inject
    AnimalTypesService animalTypesService;

    @GET
    public List<AnimalType> getAll() {
        return animalTypesService.getAll();
    }

    @GET
    @Path("{id}")
    public AnimalType get(@PathParam("id") String id) {
        return animalTypesService.get(id);
    }

    @POST
    public AnimalType save(AnimalType animalType) {  return animalTypesService.save(animalType);}

    @DELETE
    @Path("{id}")
    public AnimalType delete(@PathParam("id") String id) throws HasRelationatedDataException {
        AnimalType animalType = animalTypesService.get(id);
        if(animalType== null) throw new NotFoundException();
        animalTypesService.delete(animalType);
        return animalType;
    }


}
