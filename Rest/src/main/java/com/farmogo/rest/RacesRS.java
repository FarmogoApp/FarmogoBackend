package com.farmogo.rest;

import com.farmogo.model.Animal;
import com.farmogo.model.Race;
import com.farmogo.services.HasRelationatedDataException;
import com.farmogo.services.RaceService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("races")
public class RacesRS {
    @Inject
    RaceService raceService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Race> getAll() {
        return raceService.getAll();
    }

    @GET
    @Path("{id}")
    public Race get(@PathParam("id") String id) {
        return raceService.get(id);
    }

    @POST
    public Race save(Race race) {  return raceService.save(race);}

    @DELETE
    @Path("{id}")
    public Race delete(@PathParam("id") String id) throws HasRelationatedDataException {
        Race race = raceService.get(id);
        if(race== null) throw new NotFoundException();
        raceService.delete(race);
        return race;
    }

    @GET
    @Path("test")
    public String test(){

        Race race = new Race();

        race.setName("Pirenaica");
        race.setUuid("555");

        raceService.save(race);

        return "ok";
    }

}