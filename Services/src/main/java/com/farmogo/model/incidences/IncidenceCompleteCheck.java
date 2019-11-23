package com.farmogo.model.incidences;

public class IncidenceCompleteCheck implements IncidenceVisitor {


    boolean check;

    public boolean check(Incidence incidence){
        check =  incidence.getFarmId()!=null;
        if (check) incidence.accept(this);
        return check;
    }


    @Override
    public void visit(IncidenceGetoff obj) {
        check = obj.getGetoffType()!=null &&
                obj.getHealthRegister()!=null &&
                !obj.getHealthRegister().isEmpty();
    }

    @Override
    public void visit(IncidencePregnancy obj) {
        check = obj.getPregnancyType()!=null;
    }

    @Override
    public void visit(IncidenceTreatment obj) {
        check = obj.getDose() !=null &&
                !obj.getDose().isEmpty() &&
                obj.getMedicine() !=null &&
                !obj.getMedicine().isEmpty() &&
                obj.getTreatmentType()!=null;
    }

    @Override
    public void visit(IncidenceWeight obj) {
        check = obj.getWeight()>0;
    }
}
