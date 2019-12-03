package com.farmogo.model;

import java.io.Serializable;
import java.time.LocalDate;

public class FarmBook implements Serializable {

    private String uuid;
    private String farmOfficialId;
    private String officialId;
    private LocalDate birthDate;
    private String raceId;
    private String sex;
    private String motherOfficialId;
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

    public FarmBook() {
    }

    public String getFarmOfficialId() {
        return farmOfficialId;
    }

    public void setFarmOfficialId(String farmOfficialId) {
        this.farmOfficialId = farmOfficialId;
    }

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMotherOfficialId() {
        return motherOfficialId;
    }

    public void setMotherOfficialId(String motherOfficialId) {
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
