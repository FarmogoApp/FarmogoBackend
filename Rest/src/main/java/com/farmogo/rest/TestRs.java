package com.farmogo.rest;

import com.farmogo.model.*;
import com.farmogo.model.incidences.*;
import com.farmogo.services.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.Arrays;

@RequestScoped
@Path("test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class TestRs {
    @Inject
    AdminService adminService;

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
        /* Drop All database */
        adminService.clearDatabase();

        User user = new User();
        user.setUuid("1dd6e8a242811bf1eecf90ac");

        /*Create animal types*/
        AnimalType animalType = new AnimalType();
        animalType.setDescription("Cow");
        animalType.setIcon("CowIcon");
        AnimalType animalTypeId = animalTypesService.save(animalType);

        AnimalType animalType2 = new AnimalType();
        animalType2.setDescription("Bull");
        animalType2.setIcon("BullIcon");
        AnimalType animalTypeId2 = animalTypesService.save(animalType2);

        AnimalType animalType3 = new AnimalType();
        animalType3.setDescription("Calf");
        animalType3.setIcon("CalfIcon");
        AnimalType animalTypeId3 = animalTypesService.save(animalType3);


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
        {

            /*Create Farms, Buildings and Divisions */
            Farm farm = new Farm();
            farm.setOfficialId("1234");
            farm.setName("farm ");
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
            b1.setDivisions(Arrays.asList(d11, d12));

            Building b2 = new Building();
            b2.setName("Build 2");
            Division d21 = new Division();
            d21.setName("division 2.1");
            Division d22 = new Division();
            d22.setName("division 2.2");
            b2.setDivisions(Arrays.asList(d21, d22));
            farm.setBuildings(Arrays.asList(b1, b2));
            Farm farmA = farmService.save(farm);

            /*Create animals*/
            Animal animal = new Animal();
            animal.setOfficialId("1234567890123");
            animal.setOrigin("Lleida");
            animal.setSex("Female");
            animal.setAnimalTypeId(animalTypeId.getAnimalType());
            animal.setRaceId(raceA.getUuid());
            animal.setFarmId(farmA.getUuid());
            animal.setOfficialId("ES001202059231");
            animal.setBirthDay(LocalDate.of(2018, 1, 1));
            animal.setDivisionId(farmA.getBuildings().get(0).getDivisions().get(0).getUuid());
            // animal.setMotherId("");
            Animal animalA = animalService.save(animal);

            Animal animal2 = new Animal();
            animal2.setOfficialId("78901234567890");
            animal2.setOrigin("Ainsa");
            animal2.setSex("Male");
            animal2.setAnimalTypeId(animalTypeId2.getAnimalType());
            animal2.setRaceId(raceB.getUuid());
            animal2.setFarmId(farmA.getUuid());
            animal2.setOfficialId("ES051400239970");
            animal2.setMotherId(animalA.getUuid());
            animal2.setBirthDay(LocalDate.of(2019, 9, 1));
            animal2.setDivisionId(farmA.getBuildings().get(0).getDivisions().get(1).getUuid());
            Animal animalB = animalService.save(animal2);


            IncidenceWeight incidence = new IncidenceWeight();
            incidence.setDone(true);
            incidence.setWeight(100);
            incidence.setAnimalId(animalA.getUuid());
            incidence.setCreatedBy(user.getUuid());
            incidence.setFarmId(farmA.getUuid());
            incidencesService.save(incidence);

            IncidenceDischarge incidenceDischarge = new IncidenceDischarge();
            incidenceDischarge.setHealthRegister("test register");
            incidenceDischarge.setDischargeType(DischargeType.Slaughterhouse);
            incidenceDischarge.setObservations("observations");
            incidenceDischarge.setDone(false);
            incidenceDischarge.setAnimalId(animalA.getUuid());
            incidenceDischarge.setCreatedBy(user.getUuid());
            incidenceDischarge.setFarmId(farmA.getUuid());
            incidencesService.save(incidenceDischarge);

            incidenceDischarge.setHealthRegister("test register updated");
            incidenceDischarge.setDueDate(LocalDate.now());
            incidenceDischarge.setAnimalId(animalB.getUuid());
            incidenceDischarge.setFarmId(farmA.getUuid());
            incidencesService.save(incidenceDischarge);

            IncidencePregnancy incidencePregnancy = new IncidencePregnancy();
            incidencePregnancy.setPregnancyType(PregnancyType.Zeal);
            incidencePregnancy.setCreatedBy(user.getUuid());
            incidencePregnancy.setFarmId(farm.getUuid());
            incidencePregnancy.setAnimalId(animalB.getUuid());
            incidencesService.save(incidencePregnancy);

            IncidenceTreatment incidenceTreatment = new IncidenceTreatment();
            incidenceTreatment.setTreatmentType(TreatmentType.Vaccine);
            incidenceTreatment.setMedicine("tetanus");
            incidenceTreatment.setDose("100mg");
            incidenceTreatment.setCreatedBy(user.getUuid());
            incidenceTreatment.setAnimalId(animalA.getUuid());
            incidenceTreatment.setFarmId(farmA.getUuid());
            incidencesService.save(incidenceTreatment);


            IncidenceTreatment incidenceTreatmentIncomplete = new IncidenceTreatment();
            incidenceTreatmentIncomplete.setTreatmentType(TreatmentType.Vaccine);
            incidenceTreatmentIncomplete.setCreatedBy(user.getUuid());
            incidenceTreatmentIncomplete.setAnimalId(animalA.getUuid());
            incidenceTreatmentIncomplete.setFarmId(farmA.getUuid());
            incidencesService.save(incidenceTreatmentIncomplete);
        }

        // FARM 2
        {
            /*Create Farms, Buildings and Divisions */
            Farm farm = new Farm();
            farm.setOfficialId("7896");
            farm.setName("farm 2");
            AnimalCounter counter = new AnimalCounter();
            counter.setCounter(1234);
            counter.setPrefix("LL");
            farm.setAnimalCounter(counter);

            Building b1 = new Building();
            b1.setName("Build 1");
            Division d11 = new Division();
            d11.setName("division 1.1");
            Division d12 = new Division();
            d12.setName("division 1.2");
            b1.setDivisions(Arrays.asList(d11, d12));

            Building b2 = new Building();
            b2.setName("Build 2");
            Division d21 = new Division();
            d21.setName("division 2.1");
            Division d22 = new Division();
            d22.setName("division 2.2");
            b2.setDivisions(Arrays.asList(d21, d22));
            farm.setBuildings(Arrays.asList(b1, b2));
            Farm farmA = farmService.save(farm);

            Animal animal = new Animal();
            animal.setOfficialId("573457456543634");
            animal.setOrigin("Lleida");
            animal.setSex("Female");
            animal.setAnimalTypeId(animalTypeId.getAnimalType());
            animal.setRaceId(raceA.getUuid());
            animal.setFarmId(farmA.getUuid());
            animal.setOfficialId("ES542356436432");
            animal.setBirthDay(LocalDate.of(2019, 12, 17));
            animal.setDivisionId(farmA.getBuildings().get(0).getDivisions().get(0).getUuid());
            // animal.setMotherId("");
            Animal animalA = animalService.save(animal);

            IncidenceWeight incidence = new IncidenceWeight();
            incidence.setDone(true);
            incidence.setWeight(100);
            incidence.setAnimalId(animalA.getUuid());
            incidence.setCreatedBy(user.getUuid());
            incidence.setFarmId(farmA.getUuid());
            incidencesService.save(incidence);

            IncidenceTreatment incidenceTreatment = new IncidenceTreatment();
            incidenceTreatment.setTreatmentType(TreatmentType.Vaccine);
            incidenceTreatment.setMedicine("tetanus");
            incidenceTreatment.setDose("100mg");
            incidenceTreatment.setCreatedBy(user.getUuid());
            incidenceTreatment.setAnimalId(animalA.getUuid());
            incidenceTreatment.setFarmId(farmA.getUuid());
            incidencesService.save(incidenceTreatment);

        }


        return "ok";

    }


}
