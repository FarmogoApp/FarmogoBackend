package com.farmogo.dao.mongo.dto;

import com.farmogo.model.Animal;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnimalMongoDTO {

    @BsonId
    private ObjectId uuid;
    private String animalTypeId;
    private String officialId;
    private String tagId;
    private String sex;
    private ObjectId raceId;
    private LocalDate birthDay;
    private LocalDate dischargeDate;
    private ObjectId farmId;
    private String divisionId;
    private ObjectId motherId;
    private LocalDateTime createdLocalDateTime;
    private String origin;

    public static AnimalMongoDTO convert(Animal animal) {
        return Mapper.getInstance().map(animal, AnimalMongoDTO.class);
    }

    public static Animal convert(AnimalMongoDTO animalMongoDTO) {
        return Mapper.getInstance().map(animalMongoDTO, Animal.class);
    }

    public ObjectId getUuid() {
        return uuid;
    }

    public void setUuid(ObjectId uuid) {
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

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
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
}
