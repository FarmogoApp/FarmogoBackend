package com.farmogo.model.incidences;

import com.farmogo.model.PermissionError;

public class IncidenceCompleteCheck implements IncidenceVisitor {


    boolean check;

    public boolean check(Incidence incidence) throws PermissionError {
        if (incidence.getRemoveDate() != null) return true;
        if (incidence.getFarmId() == null) return false;
        incidence.accept(this);
        return check;
    }


    @Override
    public void visit(IncidenceDischarge obj) {
        check = obj.getDischargeType() != null &&
                obj.getHealthRegister() != null &&
                !obj.getHealthRegister().isEmpty();
    }

    @Override
    public void visit(IncidencePregnancy obj) {
        check = obj.getPregnancyType() != null;
    }

    @Override
    public void visit(IncidenceTreatment obj) {
        check = obj.getDose() != null &&
                !obj.getDose().isEmpty() &&
                obj.getMedicine() != null &&
                !obj.getMedicine().isEmpty() &&
                obj.getTreatmentType() != null;
    }

    @Override
    public void visit(IncidenceWeight obj) {
        check = obj.getWeight() > 0;
    }

    @Override
    public void visit(IncidenceBirth obj) {
        check = obj.getChildOfficialId() != null &&
                !obj.getChildOfficialId().isEmpty() &&
                obj.getSex() != null &&
                !obj.getSex().isEmpty() &&
                obj.getRaceId() != null &&
                !obj.getRaceId().isEmpty() &&
                obj.getBirthDate() != null;
    }

}
