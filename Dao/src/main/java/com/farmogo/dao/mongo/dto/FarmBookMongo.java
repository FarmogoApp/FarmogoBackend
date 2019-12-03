package com.farmogo.dao.mongo.dto;

import com.farmogo.model.Animal;
import com.farmogo.model.FarmBook;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FarmBookMongo {

    @BsonId
    private ObjectId uuid;
    private ObjectId farmOfficialId;
    private ObjectId officialId;
    private LocalDate birthDate;
    private ObjectId raceId;
    private String sex;
    private ObjectId motherOfficialId;
    private LocalDate enrollmentDate;
    private String enrollmentCause;
    private String enrollmentOrigin;
    private String enrollmentSanitaryRegister;
    private LocalDate dischargeDate;
    private String dischargeCause;
    private String dischargeCuase;
    private String dischargeDestination;
    private String dischargeSanitaryRegister;
    private LocalDate dateBonus1;
    private LocalDate dateBonus2;

    public static FarmBookMongo convert(FarmBook farmBook) {
        return Mapper.getInstance().map(farmBook, FarmBookMongo.class);
    }

    public static FarmBook convert(FarmBookMongo farmBookMongo) {
        return Mapper.getInstance().map(farmBookMongo, FarmBook.class);
    }

    public ObjectId getUuid() {
        return uuid;
    }

    public void setUuid(ObjectId uuid) {
        this.uuid = uuid;
    }

    public ObjectId getFarmOfficialId() {
        return farmOfficialId;
    }

    public void setFarmOfficialId(ObjectId farmOfficialId) {
        this.farmOfficialId = farmOfficialId;
    }

    public ObjectId getOfficialId() {
        return officialId;
    }

    public void setOfficialId(ObjectId officialId) {
        this.officialId = officialId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ObjectId getRaceId() {
        return raceId;
    }

    public void setRaceId(ObjectId raceId) {
        this.raceId = raceId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public ObjectId getMotherOfficialId() {
        return motherOfficialId;
    }

    public void setMotherOfficialId(ObjectId motherOfficialId) {
        this.motherOfficialId = motherOfficialId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getEnrollmentCause() {
        return enrollmentCause;
    }

    public void setEnrollmentCause(String enrollmentCause) {
        this.enrollmentCause = enrollmentCause;
    }

    public String getEnrollmentOrigin() {
        return enrollmentOrigin;
    }

    public void setEnrollmentOrigin(String enrollmentOrigin) {
        this.enrollmentOrigin = enrollmentOrigin;
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

    public String getDischargeCuase() {
        return dischargeCuase;
    }

    public void setDischargeCuase(String dischargeCuase) {
        this.dischargeCuase = dischargeCuase;
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
}
