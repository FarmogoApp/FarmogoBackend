package com.farmogo.rest;

import com.farmogo.model.AnimalType;
import com.farmogo.model.incidences.*;
import com.farmogo.services.IncidencesService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;


@RequestScoped
@Path("incidence")
public class IncidencesRS {
    @Inject
    IncidencesService incidencesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Incidence> getAll() {
        return incidencesService.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(Incidence incidence) { incidencesService.save(incidence);}

    @GET
    @Path("test")
    public String test() {
        IncidenceWeight incidence = new IncidenceWeight();
        incidence.setDone(true);
        incidence.setWeight(100);
        incidencesService.save(incidence);


        IncidenceGetoff incidenceGetoff = new IncidenceGetoff();
        incidenceGetoff.setHealthRegister("test register");
        incidenceGetoff.setGetoffType(GetoffType.Slaughterhouse);
        incidenceGetoff.setObservations("observations");
        incidence.setDone(false);
        incidencesService.save(incidenceGetoff);

        incidenceGetoff.setHealthRegister("test register updated");
        incidenceGetoff.setDueDate(LocalDate.now());
        incidencesService.save(incidenceGetoff);

        return "ok";
    }
}