package com.farmogo.dao.mongo.dto;

import com.farmogo.model.Animal;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnimalMongo {

    @BsonId
    private ObjectId uuid;
    private ObjectId animalTypeId;
    private String officialId;
    private String tagId;
    private String sex;
    private ObjectId raceId;
    private LocalDate birthDay;
    private LocalDate dischargeDate;
    private ObjectId farmId;
    private ObjectId divisionId;
    private ObjectId motherId;
    private LocalDateTime createdLocalDateTime;
    private String origin;

    public static AnimalMongo convert(Animal animal) {
        return Mapper.getInstance().map(animal, AnimalMongo.class);
    }

    public static Animal convert(AnimalMongo animalMongo) {
        return Mapper.getInstance().map(animalMongo, Animal.class);
    }

    public ObjectId getUuid() {
        return uuid;
    }

    public void setUuid(ObjectId uuid) {
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

    public ObjectId getRaceId() {
        return raceId;
    }

    public void setRaceId(ObjectId raceId) {
        this.raceId = raceId;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public ObjectId getFarmId() {
        return farmId;
    }

    public void setFarmId(ObjectId farmId) {
        this.farmId = farmId;
    }

    public ObjectId getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(ObjectId divisionId) {
        this.divisionId = divisionId;
    }

    public ObjectId getMotherId() {
        return motherId;
    }

    public void setMotherId(ObjectId motherId) {
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

    public ObjectId getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(ObjectId animalTypeId) {
        this.animalTypeId = animalTypeId;
    }
}