package com.farmogo.dao.mongo.dto;

import com.farmogo.model.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;

import java.time.LocalDateTime;
import java.util.Date;

public class AnimalMongoDTO {

    @BsonId
    private String uuid;
    private String animalTypeId;
    private String officialId;
    private String tagId;
    private String sex;
    private String raceId;
    private Date birthDay;
    private Date dischargeDate;
    private String farmId;
    private String divisionId;
    private String motherId;
    private LocalDateTime createdLocalDateTime;
    private String origin;

    public AnimalMongoDTO(){}

    public AnimalMongoDTO(Animal animal) {
        this.uuid = animal.getUuid();
        this.animalTypeId = animal.getAnimalTypeId();
        this.officialId = animal.getOfficialId();
        this.tagId = animal.getTagId();
        this.sex = animal.getSex();
        this.raceId = animal.getRaceId();
        this.birthDay = animal.getBirthDay();
        this.dischargeDate = animal.getDischargeDate();
        this.farmId = animal.getFarmId();
        this.divisionId = animal.getDivisionId();
        this.motherId = animal.getMotherId();
        this.createdLocalDateTime = animal.getCreatedLocalDateTime();
        this.origin = animal.getOrigin();
    }


    @BsonIgnore
    public Animal getObject(){
        Animal animal = new Animal();

        animal.setUuid(this.uuid);
        animal.setAnimalTypeId(this.animalTypeId);
        animal.setOfficialId(this.officialId);
        animal.setTagId(this.tagId);
        animal.setSex(this.sex);
        animal.setRaceId(this.raceId);
        animal.setBirthDay(this.birthDay);
        animal.setDischargeDate(this.dischargeDate);
        animal.setFarmId(this.farmId);
        animal.setDivisionId(this.divisionId);
        animal.setMotherId(this.motherId);
        animal.setCreatedLocalDateTime(this.createdLocalDateTime);
        animal.setOrigin(this.origin);

        return animal;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(String animalTypeId) {
        this.animalTypeId = animalTypeId;
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

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
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

}
