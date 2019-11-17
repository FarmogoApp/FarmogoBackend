package com.farmogo.rest;

import com.farmogo.model.Animal;
import com.farmogo.model.incidences.*;
import com.farmogo.services.IncidencesService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    @GET
    @Path("test")
    public String test() {

        Animal animal = new Animal();
        animal.setUuid("5dd0227098aa388b4499f5b9");

        IncidenceWeight incidence = new IncidenceWeight();
        incidence.setDone(true);
        incidence.setWeight(100);
        incidence.setAnimal(animal);
        incidencesService.save(incidence);


        IncidenceGetoff incidenceGetoff = new IncidenceGetoff();
        incidenceGetoff.setHealthRegister("test register");
        incidenceGetoff.setGetoffType(GetoffType.Slaughterhouse);
        incidenceGetoff.setObservations("observations");
        incidenceGetoff.setDone(false);
        incidenceGetoff.setAnimal(animal);
        incidencesService.save(incidenceGetoff);

        incidenceGetoff.setHealthRegister("test register updated");
        incidenceGetoff.setDueDate(LocalDate.now());
        incidenceGetoff.setAnimal(animal);
        incidencesService.save(incidenceGetoff);

        IncidencePregnancy incidencePregnancy = new IncidencePregnancy();
        incidencePregnancy.setPregnancyType(PregnancyType.Zeal);
        incidencesService.save(incidencePregnancy);

        IncidenceTreatment incidenceTreatment = new IncidenceTreatment();
        incidenceTreatment.setTreatmentType(TreatmentType.Vaccine);
        incidenceTreatment.setMedicine("tetanus");
        incidenceTreatment.setDose("100mg");
        incidencesService.save(incidenceTreatment);

        return "ok";
    }
}