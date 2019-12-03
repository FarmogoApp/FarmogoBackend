package com.farmogo.dao.mongo.dto;

import com.farmogo.model.Animal;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnimalMongo {

    @BsonId
    private ObjectId uuid;
    private ObjectId farmId;
    private String officialId;
    private LocalDate birthDay;
    private String sex;
    private ObjectId raceId;
    private String motherOfficialId;
    private LocalDate enrrollementDate;
    private String enrollmentCause;
    private String origin;
    private String enrollmentSanitaryRegister;
    private LocalDate dischargeDate;
    private String dischargeCause;
    private String dischargeDestination;
    private String dischargeSanitaryRegister;
    private LocalDate dateBonus1;
    private LocalDate dateBonus2;
    private ObjectId animalTypeId;
    private String tagId;
    private String motherId;
    private LocalDateTime createdLocalDateTime;
    private ObjectId divisionId;

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

    public ObjectId getFarmId() {
        return farmId;
    }

    public void setFarmId(ObjectId farmId) {
        this.farmId = farmId;
    }

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
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

    public String getMotherOfficialId() {
        return motherOfficialId;
    }

    public void setMotherOfficialId(String motherOfficialId) {
        this.motherOfficialId = motherOfficialId;
    }

    public LocalDate getEnrrollementDate() {
        return enrrollementDate;
    }

    public void setEnrrollementDate(LocalDate enrrollementDate) {
        this.enrrollementDate = enrrollementDate;
    }

    public String getEnrollmentCause() {
        return enrollmentCause;
    }

    public void setEnrollmentCause(String enrollmentCause) {
        this.enrollmentCause = enrollmentCause;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getEnrollmentSanitaryRegister() {
        return enrollmentSanitaryRegister;
    }

    public void setEnrollmentSanitaryRegister(String enrollmentSanitaryRegister) {
        this.enrollmentSanitaryRegister = enrollmentSanitaryRegister;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getDischargeCause() {
        return dischargeCause;
    }

    public void setDischargeCause(String dischargeCause) {
        this.dischargeCause = dischargeCause;
    }

    public String getDischargeDestination() {
        return dischargeDestination;
    }

    public void setDischargeDestination(String dischargeDestination) {
        this.dischargeDestination = dischargeDestination;
    }

    public String getDischargeSanitaryRegister() {
        return dischargeSanitaryRegister;
    }

    public void setDischargeSanitaryRegister(String dischargeSanitaryRegister) {
        this.dischargeSanitaryRegister = dischargeSanitaryRegister;
    }

    public LocalDate getDateBonus1() {
        return dateBonus1;
    }

    public void setDateBonus1(LocalDate dateBonus1) {
        this.dateBonus1 = dateBonus1;
    }

    public LocalDate getDateBonus2() {
        return dateBonus2;
    }

    public void setDateBonus2(LocalDate dateBonus2) {
        this.dateBonus2 = dateBonus2;
    }

    public ObjectId getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(ObjectId animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
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

    public ObjectId getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(ObjectId divisionId) {
        this.divisionId = divisionId;
    }
}
