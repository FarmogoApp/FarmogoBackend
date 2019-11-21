package com.farmogo.rest;

import com.farmogo.model.*;
import com.farmogo.model.incidences.*;
import com.farmogo.services.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.soap.Addressing;
import java.time.LocalDate;
import java.util.Arrays;

@RequestScoped
@Path("test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class TestRs {
    @Inject
    AnimalTypesService animalTypesService;

    @Inject
    AnimalService animalService;

    @Inject
    FarmService farmService;

    @Inject
    RaceService raceService;

    @Inject
    IncidencesService incidencesService;

    @GET
    @Path("database")
    public String test() {
        /*Create animal types*/
        AnimalType animalType = new AnimalType();
        animalType.setDescription("Cow");
        animalType.setIcon("CowIcon");
        AnimalType animalTypeId = animalTypesService.save(animalType);
        animalTypesService.save(animalType);

        AnimalType animalType2 = new AnimalType();
        animalType2.setDescription("Bull");
        animalType2.setIcon("BullIcon");
        AnimalType animalTypeId2 = animalTypesService.save(animalType2);
        animalTypesService.save(animalType2);

        AnimalType animalType3 = new AnimalType();
        animalType3.setDescription("Calf");
        animalType3.setIcon("CalfIcon");
        AnimalType animalTypeId3= animalTypesService.save(animalType3);
        animalTypesService.save(animalType3);


        /*Create races*/
        Race race = new Race();
        race.setName("Blonda de Aquitania");
        Race raceA = raceService.save(race);

        Race race2 = new Race();
        race2.setName("Charolesa");
        Race raceB = raceService.save(race2);

        Race race3 = new Race();
        race3.setName("Fleckvieh");
        Race raceC = raceService.save(race3);

        /*Create Farms, Buildings and Divisions */
        Farm farm = new Farm();
        farm.setOfficialId("1234");
        farm.setName("farm 1");
        AnimalCounter counter = new AnimalCounter();
        counter.setCounter(555);
        counter.setPrefix("HU");
        farm.setAnimalCounter(counter);

        Building b1 = new Building();
        b1.setName("Build 1");
        Division d11 = new Division();
        d11.setName("division 1.1");
        Division d12 = new Division();
        d12.setName("division 1.2");
        b1.setDivisions(Arrays.asList(d11,d12));

        Building b2 = new Building();
        b2.setName("Build 2");
        Division d21 = new Division();
        d21.setName("division 2.1");
        Division d22 = new Division();
        d22.setName("division 2.2");
        b2.setDivisions(Arrays.asList(d21,d22));
        farm.setBuildings(Arrays.asList(b1,b2));
        Farm farmA = farmService.save(farm);

        /*Create animals*/
        Animal animal = new Animal();
        animal.setOrigin("Lleida");
        animal.setSex("Female");
        animal.setAnimalTypeId(animalTypeId.getAnimalType());
        animal.setRaceId(raceA.getUuid());
        animal.setFarmId(farmA.getUuid());
        animal.setMotherId("");
        Animal animalA = animalService.save(animal);

        Animal animal2 = new Animal();
        animal2.setOrigin("Ainsa");
        animal2.setSex("Male");
        animal2.setAnimalTypeId(animalTypeId.getAnimalType());
        animal2.setRaceId(raceB.getUuid());
        animal2.setFarmId(farmA.getUuid());
        animal2.setMotherId("");
        Animal animalB = animalService.save(animal2);

        User user = new User();
        user.setUuid("5dd0227098aa388b4499f5b0");

        IncidenceWeight incidence = new IncidenceWeight();
        incidence.setDone(true);
        incidence.setWeight(100);
        incidence.setAnimal(animalA);
        incidence.setCreatedBy(user);
        incidence.setFarm(farmA);
        incidencesService.save(incidence);

        IncidenceGetoff incidenceGetoff = new IncidenceGetoff();
        incidenceGetoff.setHealthRegister("test register");
        incidenceGetoff.setGetoffType(GetoffType.Slaughterhouse);
        incidenceGetoff.setObservations("observations");
        incidenceGetoff.setDone(false);
        incidenceGetoff.setAnimal(animalA);
        incidenceGetoff.setCreatedBy(user);
        incidenceGetoff.setFarm(farmA);
        incidencesService.save(incidenceGetoff);

        incidenceGetoff.setHealthRegister("test register updated");
        incidenceGetoff.setDueDate(LocalDate.now());
        incidenceGetoff.setAnimal(animalB);
        incidenceGetoff.setFarm(farmA);
        incidencesService.save(incidenceGetoff);

        IncidencePregnancy incidencePregnancy = new IncidencePregnancy();
        incidencePregnancy.setPregnancyType(PregnancyType.Zeal);
        incidencePregnancy.setCreatedBy(user);
        incidencePregnancy.setFarm(farm);
        incidencePregnancy.setAnimal(animalB);
        incidencesService.save(incidencePregnancy);

        IncidenceTreatment incidenceTreatment = new IncidenceTreatment();
        incidenceTreatment.setTreatmentType(TreatmentType.Vaccine);
        incidenceTreatment.setMedicine("tetanus");
        incidenceTreatment.setDose("100mg");
        incidenceTreatment.setCreatedBy(user);
        incidenceTreatment.setAnimal(animalA);
        incidenceTreatment.setFarm(farmA);
        incidencesService.save(incidenceTreatment);

    return "ok";

    }


    }
