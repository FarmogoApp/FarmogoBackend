package com.farmogo.rest.reporting;

import java.time.LocalDate;

public class AnimalReportRow {

    private String officialId;
    private LocalDate BirthDate;
    private String race;
    private String sex;
    private String motherOfficialId;
    private LocalDate enrollmentDate;
    private String enrollmentCause;
    private String enrollmentOrigin;
    private String enrollmentSanitaryRegister;
    private LocalDate dischargeDate;
    private String dischargeCause;
    private String dischargeDestination;
    private String dischargeSanitaryRegister;
    private LocalDate dateBonus1;
    private LocalDate dateBonus2;

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId;
    }

    public LocalDate getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        BirthDate = birthDate;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
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

    public void setDischargeCause(String dischargecause) {
        this.dischargeCause = dischargecause;
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
