package com.farmogo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Animal implements Serializable {
    private String uuid;
    private String animalTypeId;
    private String officialId;
    private String tagId;
    private String sex;
    private String raceId;
    private LocalDate birthDay;
    private LocalDate dischargeDate;
    private String motherId;
    private String motherOfficialId;
    private LocalDateTime createdLocalDateTime;
    private String origin;
    private String divisionId;
    private String farmId;


    public Animal() {
        createdLocalDateTime = LocalDateTime.now();
    }

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

    public String getMotherOfficialId() {
        return motherOfficialId;
    }

    public void setMotherOfficialId(String motherOfficialId) {
        this.motherOfficialId = motherOfficialId;
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

    public String getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(String animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return uuid.equals(animal.uuid) &&
                Objects.equals(animalTypeId, animal.animalTypeId) &&
                Objects.equals(officialId, animal.officialId) &&
                Objects.equals(tagId, animal.tagId) &&
                Objects.equals(sex, animal.sex) &&
                Objects.equals(raceId, animal.raceId) &&
                Objects.equals(birthDay, animal.birthDay) &&
                Objects.equals(dischargeDate, animal.dischargeDate) &&
                Objects.equals(motherId, animal.motherId) &&
                Objects.equals(motherOfficialId, animal.motherOfficialId) &&
                Objects.equals(createdLocalDateTime, animal.createdLocalDateTime) &&
                Objects.equals(origin, animal.origin) &&
                Objects.equals(divisionId, animal.divisionId) &&
                Objects.equals(farmId, animal.farmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, animalTypeId, officialId, tagId, sex, raceId, birthDay, dischargeDate, motherId, motherOfficialId, createdLocalDateTime, origin, divisionId, farmId);
    }
}
