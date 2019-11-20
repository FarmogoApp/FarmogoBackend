package com.farmogo.rest;

import com.farmogo.model.*;
import com.farmogo.services.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.soap.Addressing;
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

    @GET
    @Path("database")
    public String test() {


        /*Create animal types*/
        AnimalType animalType = new AnimalType();
        animalType.setDescription("test");
        animalType.setIcon("icon");
        AnimalType animalTypeId = animalTypesService.save(animalType);
        animalTypesService.save(animalType);


        /*Create races*/
        Race race = new Race();
        race.setName("Blonda de Aquitania");
        raceService.save(race);

        Race race2 = new Race();
        race2.setName("Charolesa");
        raceService.save(race2);

        Race race3 = new Race();
        race3.setName("Fleckvieh");
        raceService.save(race3);

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
        farmService.save(farm);

        /*Create animals*/
        Animal animal = new Animal();
        animal.setOrigin("Lleida");
        animal.setSex("Female");
        animal.setAnimalTypeId(animalTypeId.getAnimalType());
        animal.setRaceId("");
        animal.setMotherId("");
        animalService.save(animal);

    return "ok";
    }

    }
