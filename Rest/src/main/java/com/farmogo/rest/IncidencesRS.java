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
    public void save(Incidence incidence) { incidencesService.save(incidence);}

    @GET
    @Path("test")
    public String test() {

        User user = new User();
        user.setUuid("5dd0227098aa388b4499f5b0");

        Farm farm = new Farm();
        farm.setUuid("5dd0227098aa388b4499f5b1");

        Animal animal = new Animal();
        animal.setUuid("5dd0227098aa388b4499f5b9");

        IncidenceWeight incidence = new IncidenceWeight();
        incidence.setDone(true);
        incidence.setWeight(100);
        incidence.setAnimal(animal);
        incidence.setCreatedBy(user);
        incidence.setFarm(farm);
        incidencesService.save(incidence);


        IncidenceGetoff incidenceGetoff = new IncidenceGetoff();
        incidenceGetoff.setHealthRegister("test register");
        incidenceGetoff.setGetoffType(GetoffType.Slaughterhouse);
        incidenceGetoff.setObservations("observations");
        incidenceGetoff.setDone(false);
        incidenceGetoff.setAnimal(animal);
        incidenceGetoff.setCreatedBy(user);
        incidenceGetoff.setFarm(farm);
        incidencesService.save(incidenceGetoff);

        incidenceGetoff.setHealthRegister("test register updated");
        incidenceGetoff.setDueDate(LocalDate.now());
        incidenceGetoff.setAnimal(animal);
        incidencesService.save(incidenceGetoff);

        IncidencePregnancy incidencePregnancy = new IncidencePregnancy();
        incidencePregnancy.setPregnancyType(PregnancyType.Zeal);
        incidencePregnancy.setCreatedBy(user);
        incidencePregnancy.setFarm(farm);
        incidencesService.save(incidencePregnancy);

        IncidenceTreatment incidenceTreatment = new IncidenceTreatment();
        incidenceTreatment.setTreatmentType(TreatmentType.Vaccine);
        incidenceTreatment.setMedicine("tetanus");
        incidenceTreatment.setDose("100mg");
        incidenceTreatment.setCreatedBy(user);
        incidenceTreatment.setFarm(farm);
        incidencesService.save(incidenceTreatment);

        return "ok";
    }
}