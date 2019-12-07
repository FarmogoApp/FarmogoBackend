package com.farmogo.rest;

import com.farmogo.model.*;
import com.farmogo.model.incidences.Incidence;
import com.farmogo.services.FarmService;
import com.farmogo.services.IncidencesService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("farms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FarmRs {
    @Inject
    FarmService farmService;

    @Inject
    IncidencesService incidencesService;

    @GET
    public List<Farm> getAll() {
        return farmService.getFarms();
    }

    @GET
    @Path("{id}")
    public Farm get(@PathParam("id") String id) {
        try {
            Farm farm = farmService.get(id);
            if (farm == null) throw new NotFoundException();
            return farm;
        } catch (AccessNotAllowed accessNotAllowed) {
            throw new ForbiddenException();
        }
    }

    @GET
    @Path("{id}/lastIncidences")
    public List<Incidence> getLastIncidences(@PathParam("id") String farmId,
                                             @QueryParam("limit") @DefaultValue("10") int limit) {
        try {
            Farm farm = farmService.get(farmId);
            if (farm == null) throw new NotFoundException();
            return incidencesService.getLast(farmId, limit);
        } catch (AccessNotAllowed accessNotAllowed) {
            throw new ForbiddenException();
        }
    }


    @POST
    public Farm save(Farm farm) {
        try {
            return farmService.save(farm);
        } catch (ModificationNotAllowed ex) {
            throw new ForbiddenException();
        }
    }

    @DELETE
    @Path("{id}")
    public Farm delete(@PathParam("id") String id) {
        try {
            Farm farm = farmService.get(id);
            if (farm == null) throw new NotFoundException();
            farmService.delete(farm);
            return farm;
        } catch (DeleteNotAllowed | AccessNotAllowed ex) {
            throw new ForbiddenException();
        } catch (DeleteNotPossible deleteNotPossible) {
            throw new InternalServerErrorException("Are items that they requiere this farm");
        }
    }
}