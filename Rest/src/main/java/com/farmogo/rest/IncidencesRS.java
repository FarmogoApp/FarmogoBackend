package com.farmogo.rest;

import com.farmogo.model.Animal;
import com.farmogo.model.Farm;
import com.farmogo.model.User;
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
    public void save(Incidence incidence) { 
    	incidencesService.save(incidence);
    }

    @GET
    @Path("test")
    public String test() {

        String user ="5dd0227098aa388b4499f5b0";
        String farm = "5dd0227098aa388b4499f5b1";
        String animal = "5dd0227098aa388b4499f5b9";

        IncidenceWeight incidence = new IncidenceWeight();
        incidence.setDone(true);
        incidence.setWeight(100);
        incidence.setAnimalId(animal);
        incidence.setCreatedBy(user);
        incidence.setFarmId(farm);
        incidencesService.save(incidence);


        IncidenceGetoff incidenceGetoff = new IncidenceGetoff();
        incidenceGetoff.setHealthRegister("test register");
        incidenceGetoff.setGetoffType(GetoffType.Slaughterhouse);
        incidenceGetoff.setObservations("observations");
        incidenceGetoff.setDone(false);
        incidenceGetoff.setAnimalId(animal);
        incidenceGetoff.setCreatedBy(user);
        incidenceGetoff.setFarmId(farm);
        incidencesService.save(incidenceGetoff);

        incidenceGetoff.setHealthRegister("test register updated");
        incidenceGetoff.setDueDate(LocalDate.now());
        incidenceGetoff.setAnimalId(animal);
        incidencesService.save(incidenceGetoff);

        IncidencePregnancy incidencePregnancy = new IncidencePregnancy();
        incidencePregnancy.setPregnancyType(PregnancyType.Zeal);
        incidencePregnancy.setCreatedBy(user);
        incidencePregnancy.setFarmId(farm);
        incidencesService.save(incidencePregnancy);

        IncidenceTreatment incidenceTreatment = new IncidenceTreatment();
        incidenceTreatment.setTreatmentType(TreatmentType.Vaccine);
        incidenceTreatment.setMedicine("tetanus");
        incidenceTreatment.setDose("100mg");
        incidenceTreatment.setCreatedBy(user);
        incidenceTreatment.setFarmId(farm);
        incidencesService.save(incidenceTreatment);

        return "ok";
    }
}