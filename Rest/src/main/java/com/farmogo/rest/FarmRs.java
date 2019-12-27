package com.farmogo.rest;

import com.farmogo.model.*;
import com.farmogo.model.incidences.Incidence;
import com.farmogo.rest.reporting.AnimalReport;
import com.farmogo.rest.reporting.AnimalReporting;
import com.farmogo.services.FarmService;
import com.farmogo.services.IncidencesService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
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

    @Inject
    AnimalReporting animalReporting;

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
            throw new ForbiddenException(accessNotAllowed);
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
            throw new ForbiddenException(accessNotAllowed);
        }
    }

    @GET
    @Path("{id}/report")
    @Consumes(MediaType.MEDIA_TYPE_WILDCARD)
    @Produces("application/pdf")
    public Response generateReport(@PathParam("id") String farmId){
        System.out.println("REPORT");

        try {
            Farm farm = farmService.get(farmId);
            if (farm == null) throw new NotFoundException();
            StreamingOutput stream = outputStream -> animalReporting.generate(farm, outputStream);
            return Response.ok(stream).build();
        } catch (AccessNotAllowed accessNotAllowed) {
            throw new ForbiddenException(accessNotAllowed);
        }
    }


    @POST
    public Farm save(Farm farm) {
        try {
            return farmService.save(farm);
        } catch (ModificationNotAllowed ex) {
            throw new ForbiddenException(ex);
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
            throw new ForbiddenException(ex);
        } catch (DeleteNotPossible deleteNotPossible) {
            throw new InternalServerErrorException("Are items that they requiere this farm", deleteNotPossible);
        }
    }


    @GET
    @Path("{id}/subscription")
    public LocalDate getSubscription(@PathParam("id") String id) {
        try {
            Farm farm = farmService.get(id);
            if (farm == null) throw new NotFoundException();;
            return farm.getSubscriptionExpiration();
        } catch (AccessNotAllowed ex) {
            throw new ForbiddenException(ex);
        }
    }

    @PUT
    @Path("{id}/subscription")
    public LocalDate renewSubscription(@PathParam("id") String id, int months) {
        try {
            Farm farm = farmService.get(id);
            if (farm == null) throw new NotFoundException();
            farm.setSubscriptionExpiration(LocalDate.now().plusMonths(months));
            farmService.save(farm);
            return farm.getSubscriptionExpiration();
        } catch (AccessNotAllowed | ModificationNotAllowed ex) {
            throw new ForbiddenException(ex);
        }
    }
}