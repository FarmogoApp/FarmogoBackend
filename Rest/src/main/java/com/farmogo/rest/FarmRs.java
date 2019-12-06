package com.farmogo.rest;

import com.farmogo.model.*;
import com.farmogo.services.FarmService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@RequestScoped
@Path("farms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FarmRs {
    @Inject
    FarmService farmService;

    @GET
    public List<Farm> getAll() {
        return farmService.getFarms();
    }

    @GET
    @Path("{id}")
    public Farm get(@PathParam("id") String id) {
        Farm farm = farmService.get(id);
        if (farm == null) throw new ForbiddenException();
        return farm;
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
        Farm farm = farmService.get(id);
        if (farm == null) throw new NotFoundException();
        try {
            farmService.delete(farm);
        } catch (DeleteNotAllowed ex) {
            throw new ForbiddenException();
        } catch (DeleteNotPossible deleteNotPossible) {
            throw new InternalServerErrorException("Are items that they requiere this farm");
        }
        return farm;
    }
}