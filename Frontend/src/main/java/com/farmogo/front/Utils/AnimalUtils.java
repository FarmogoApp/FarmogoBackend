package com.farmogo.front.Utils;

import com.farmogo.model.Animal;
import com.farmogo.model.AnimalType;
import com.farmogo.model.Farm;
import com.farmogo.model.Race;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnimalUtils {

    private List<Animal> animalList;
    private List<Race> raceList;
    private List<AnimalType> animalTypeList;
    private List<Farm> farmList;

    public AnimalUtils(){}

    public AnimalUtils(List<Animal> animalList, List<Race> raceList, List<AnimalType> animalTypeList, List<Farm> farmList) {
        this.animalList = animalList;
        this.raceList = raceList;
        this.animalTypeList = animalTypeList;
        this.farmList = farmList;
    }


    public List<Animal> getMothersList(){
        return animalList.stream()
                .filter(p -> p.getSex().equals("Female"))
                .collect(Collectors.toList());
    }

    public String motherIdToOfficialId(String motherId){
        Optional<Animal> animalMother = animalList.stream()
                .filter(p -> p.getUuid().equals(motherId))
                .findFirst();

        return animalMother.isPresent() ? animalMother.get().getOfficialId() : "";
    }

    public String raceIdToName(String raceId){
        Optional<Race> race = raceList.stream()
                .filter(p -> p.getUuid().equals(raceId))
                .findFirst();

        return race.isPresent() ? race.get().getName() : "";
    }

    public String animalTypeToDescription(String animalType){
        Optional<AnimalType> at = animalTypeList.stream()
                .filter(p -> p.getAnimalType().equals(animalType))
                .findFirst();

        return at.isPresent() ? at.get().getDescription() : "";
    }

    public String farmIdToOfficialId(String farmId){
        Optional<Farm> farm = farmList.stream()
                .filter(p -> p.getUuid().equals(farmId))
                .findFirst();

        return farm.isPresent() ? farm.get().getOfficialId() : "";
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public void setRaceList(List<Race> raceList) {
        this.raceList = raceList;
    }

    public void setAnimalTypeList(List<AnimalType> animalTypeList) {
        this.animalTypeList = animalTypeList;
    }

    public void setFarmList(List<Farm> farmList) {
        this.farmList = farmList;
    }


}
