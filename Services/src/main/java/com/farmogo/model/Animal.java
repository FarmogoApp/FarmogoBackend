package com.farmogo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Animal implements Serializable {
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


    public Animal(){ uuid = UUID.randomUUID().toString(); }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType= animalType;
    }

    public Races getRaces() {
        return races;
    }

    public void setRacesList(Races races) {
        this.races = races;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Divisions getDivisionsList() {
        return divisions;
    }

    public void setDivisionsList(Divisions divisions) {
        this.divisions = divisions;
    }

    public void setRaces(Races races) {
        this.races = races;
    }

    public Divisions getDivisions() {
        return divisions;
    }

    public void setDivisions(Divisions divisions) {
        this.divisions = divisions;
    }

}
