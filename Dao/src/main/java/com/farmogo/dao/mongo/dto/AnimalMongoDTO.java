package com.farmogo.dao.mongo.dto;

import com.farmogo.model.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;

import java.time.LocalDateTime;
import java.util.Date;

public class AnimalMongoDTO {

    @BsonId
    private String uuid;
    private AnimalType animalType;
    private String officialId;
    private String tagId;
    private String sex;
    private String raceId;
    private Date birthDay;
    private Date dischargeDate;
    private String  idFarm;
    private String idDivision;
    private String motherId;
    private LocalDateTime createdLocalDateTime;
    private String origin;
    private Races races;
    private Farm farm;
    private Divisions divisions;


    public AnimalMongoDTO(Animal animal) {
        this.uuid = animal.getUuid();
        this.animalType = animal.getAnimalType();
        this.officialId = animal.getOfficialId();
        this.tagId = animal.getTagId();
        this.sex = animal.getSex();
        this.raceId = animal.getRaceId();
        this.birthDay = animal.getBirthDay();
        this.dischargeDate = animal.getDischargeDate();
        this.idFarm = animal.getIdFarm();
        this.idDivision = animal.getIdDivision();
        this.motherId = animal.getMotherId();
        this.createdLocalDateTime = animal.getCreatedLocalDateTime();
        this.origin = animal.getOrigin();
        this.races = animal.getRaces();
        this.farm = animal.getFarm();
        this.divisions = animal.getDivisions();
    }


    @BsonIgnore
    public Animal getObject(){
        Animal animal = new Animal();

        animal.setUuid(this.uuid);
        animal.setAnimalType(this.animalType);
        animal.setOfficialId(this.officialId);
        animal.setTagId(this.tagId);
        animal.setSex(this.sex);
        animal.setRaceId(this.raceId);
        animal.setBirthDay(this.birthDay);
        animal.setDischargeDate(this.dischargeDate);
        animal.setIdFarm(this.idFarm);
        animal.setIdDivision(this.idDivision);
        animal.setMotherId(this.motherId);
        animal.setCreatedLocalDateTime(this.createdLocalDateTime);
        animal.setOrigin(this.origin);
        animal.setRaces(this.races);
        animal.setFarm(this.farm);
        animal.setDivisions(this.divisions);

        return animal;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getIdFarm() {
        return idFarm;
    }

    public void setIdFarm(String idFarm) {
        this.idFarm = idFarm;
    }

    public String getIdDivision() {
        return idDivision;
    }

    public void setIdDivision(String idDivision) {
        this.idDivision = idDivision;
    }

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    public LocalDateTime getCreatedLocalDateTime() {
        return createdLocalDateTime;
    }

    public void setCreatedLocalDateTime(LocalDateTime createdLocalDateTime) {
        this.createdLocalDateTime = createdLocalDateTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Races getRaces() {
        return races;
    }

    public void setRaces(Races races) {
        this.races = races;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Divisions getDivisions() {
        return divisions;
    }

    public void setDivisions(Divisions divisions) {
        this.divisions = divisions;
    }
}
