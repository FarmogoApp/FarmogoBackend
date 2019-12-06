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
    public String test() throws ModificationNotAllowed {
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

        Race race4 = new Race();
        race4.setName("Holstein");
        Race raceD = raceService.save(race4);

        Race race5 = new Race();
        race5.setName("Hereford");
        Race raceE = raceService.save(race5);

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
            animal.setOrigin("Lleida");
            animal.setSex("Female");
            animal.setAnimalTypeId(animalTypeId.getUuid());
            animal.setRaceId(raceA.getUuid());
            animal.setFarmId(farmA.getUuid());
            animal.setOfficialId("ES001202059231");
            animal.setBirthDay(LocalDate.of(2018, 1, 1));
            animal.setDivisionId(farmA.getBuildings().get(0).getDivisions().get(0).getUuid());
            // animal.setMotherId("");
            Animal animalA = animalService.save(animal);

            Animal animal2 = new Animal();
            animal2.setOrigin("Ainsa");
            animal2.setSex("Male");
            animal2.setAnimalTypeId(animalTypeId2.getUuid());
            animal2.setRaceId(raceB.getUuid());
            animal2.setFarmId(farmA.getUuid());
            animal2.setOfficialId("ES0514002399770");
            animal2.setMotherId(animalA.getUuid());
            animal2.setMotherOfficialId(animalA.getOfficialId());
            animal2.setBirthDay(LocalDate.of(2019, 9, 1));
            animal2.setDivisionId(farmA.getBuildings().get(0).getDivisions().get(1).getUuid());
            Animal animalB = animalService.save(animal2);

            Animal animal3 = new Animal();
            animal3.setOrigin("Massoteres");
            animal3.setSex("Male");
            animal3.setAnimalTypeId(animalTypeId2.getUuid());
            animal3.setRaceId(raceC.getUuid());
            animal3.setFarmId(farmA.getUuid());
            animal3.setOfficialId("ES0514002354029");
            animal3.setMotherOfficialId(animalA.getOfficialId());
            animal3.setBirthDay(LocalDate.of(2019, 9, 29));
            animal3.setDivisionId(farmA.getBuildings().get(0).getDivisions().get(1).getUuid());
            Animal animalC = animalService.save(animal3);

            Animal animal4 = new Animal();
            animal4.setOrigin("Guissona");
            animal4.setSex("Female");
            animal4.setAnimalTypeId(animalTypeId3.getUuid());
            animal4.setRaceId(raceD.getUuid());
            animal4.setFarmId(farmA.getUuid());
            animal4.setOfficialId("ES0515552354029");
            animal4.setBirthDay(LocalDate.of(2018, 9, 29));
            animal4.setDivisionId(farmA.getBuildings().get(0).getDivisions().get(1).getUuid());
            Animal animalD = animalService.save(animal4);

            Animal animal5 = new Animal();
            animal5.setOrigin("Guarda-si-venes");
            animal5.setSex("Male");
            animal5.setAnimalTypeId(animalTypeId3.getUuid());
            animal5.setRaceId(raceE.getUuid());
            animal5.setFarmId(farmA.getUuid());
            animal5.setOfficialId("ES0513332354029");
            animal5.setBirthDay(LocalDate.of(2018, 9, 29));
            animal5.setDivisionId(farmA.getBuildings().get(0).getDivisions().get(0).getUuid());
            Animal animalF = animalService.save(animal5);



            IncidenceWeight incidence = new IncidenceWeight();
            incidence.setDone(true);
            incidence.setWeight(100);
            incidence.setAnimalId(animalA.getUuid());
            incidence.setCreatedBy(user.getUuid());
            incidence.setFarmId(farmA.getUuid());
            incidence.setDate(LocalDate.of(2018, 5, 1));
            incidencesService.save(incidence);


            IncidenceTreatment incidenceTreatment = new IncidenceTreatment();
            incidenceTreatment.setTreatmentType(TreatmentType.Vaccine);
            incidenceTreatment.setMedicine("tetanus");
            incidenceTreatment.setDose("100mg");
            incidenceTreatment.setCreatedBy(user.getUuid());
            incidenceTreatment.setAnimalId(animalA.getUuid());
            incidenceTreatment.setFarmId(farmA.getUuid());
            incidenceTreatment.setDate(LocalDate.of(2018, 6, 2));
            incidencesService.save(incidenceTreatment);


            IncidenceTreatment incidenceTreatmentIncomplete = new IncidenceTreatment();
            incidenceTreatmentIncomplete.setTreatmentType(TreatmentType.Vaccine);
            incidenceTreatmentIncomplete.setCreatedBy(user.getUuid());
            incidenceTreatmentIncomplete.setAnimalId(animalA.getUuid());
            incidenceTreatmentIncomplete.setFarmId(farmA.getUuid());
            incidenceTreatmentIncomplete.setDate(LocalDate.of(2018, 7, 3));

            incidencesService.save(incidenceTreatmentIncomplete);

            IncidenceDischarge incidenceDischarge = new IncidenceDischarge();
            incidenceDischarge.setHealthRegister("test register");
            incidenceDischarge.setDischargeType(DischargeType.Slaughterhouse);
            incidenceDischarge.setObservations("observations");
            incidenceDischarge.setDone(false);
            incidenceDischarge.setAnimalId(animalA.getUuid());
            incidenceDischarge.setCreatedBy(user.getUuid());
            incidenceDischarge.setFarmId(farmA.getUuid());
            incidencesService.save(incidenceDischarge);

            IncidencePregnancy incidencePregnancy = new IncidencePregnancy();
            incidencePregnancy.setPregnancyType(PregnancyType.Zeal);
            incidencePregnancy.setCreatedBy(user.getUuid());
            incidencePregnancy.setFarmId(farm.getUuid());
            incidencePregnancy.setAnimalId(animalB.getUuid());
            incidencesService.save(incidencePregnancy);
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
            animal.setOrigin("Lleida");
            animal.setSex("Female");
            animal.setAnimalTypeId(animalTypeId.getUuid());
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
