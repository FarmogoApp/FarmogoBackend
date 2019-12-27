package com.farmogo.rest.reporting;

import java.util.List;

public class AnimalReport {

    private String farmOfficialId;
    private String observations;

    private List<AnimalReportRow> registers;



    public String getFarmOfficialId() {
        return farmOfficialId;
    }

    public void setFarmOfficialId(String farmOfficialId) {
        this.farmOfficialId = farmOfficialId;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public List<AnimalReportRow> getRegisters() {
        return registers;
    }

    public void setRegisters(List<AnimalReportRow> registers) {
        this.registers = registers;
    }
}
