package com.farmogo.front;

import com.farmogo.model.Animal;
import com.farmogo.model.incidences.*;
import com.farmogo.services.AnimalService;
import com.farmogo.services.FarmService;
import com.farmogo.services.IncidencesService;
import com.farmogo.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class IncidenceView implements Serializable {

    @Inject
    IncidencesService incidencesService;

    @Inject
    UserService userService;

    @Inject
    AnimalService animalService;

    @Inject
    FarmService farmService;

    @Inject
    AnimalDataView animalDataView;

    List<Incidence> incidenceList;

    Incidence incidence;
    String animalId;
    IncidenceType incidenceType;
    String title;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
        animalId = (String) faceletContext.getAttribute("animalId");
        if (animalId == null) {
            Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
            animalId = params.get("animalId");
        }

        updateIncidenceList();
    }

    private void updateIncidenceList() {
        if (animalId != null) {
            incidenceList = incidencesService.getAll(animalId);
            Animal animal = animalService.get(animalId);
            title = "Incidences of " + animal.getOfficialId();
        } else {
            incidenceList = incidencesService.getNotCompleted(farmService.getCurrentFarm().getUuid());
            title = "Incidences Incompleted";
        }
    }

    public String getTitle(){
        return title;
    }


    public String getAnimalId() {
        return this.animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
        incidenceList = incidencesService.getAll(animalId);
    }

    public void resetIncidence() {
        incidence = null;
    }

    public void newIncidence(IncidenceType incidenceType) {
        switch (incidenceType) {
            case WEIGHT:
                incidence = new IncidenceWeight();
                break;
            case PREGNANCY:
                incidence = new IncidencePregnancy();
                break;
            case TREATMENT:
                incidence = new IncidenceTreatment();
                break;
            case DISCHARGE:
                incidence = new IncidenceDischarge();
        }
        incidence.setAnimalId(animalId);
        incidence.setCreatedBy(userService.getCurrentUser().getUuid());
    }


    public List<Incidence> getIncidenceList() {
        return incidenceList;
    }

    public void setIncidenceList(List<Incidence> incidenceList) {
        this.incidenceList = incidenceList;
    }

    public Incidence getIncidence() {
        return incidence;
    }

    public void setIncidence(Incidence incidence) {
        this.incidence = incidence;
    }

    public IncidenceType getIncidenceType() {
        return incidenceType;
    }

    public void setIncidenceType(IncidenceType incidenceType) {
        this.incidenceType = incidenceType;
        newIncidence(incidenceType);
    }

    public String formatDate(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public void save() {
        incidencesService.save(incidence);
        updateIncidenceList();
        animalDataView.updateAnimal(animalService.get(incidence.getAnimalId()));
    }

    public IncidenceType[] getIncidenceTypes(){
        return IncidenceType.values();
    }

    public PregnancyType[] getPregnancyTypes() {
        return PregnancyType.values();
    }

    public TreatmentType[] getTreatmentTypes() {
        return TreatmentType.values();
    }

    public DischargeType[] getDischargeTypes() {
        return DischargeType.values();
    }

    public String getAnimalOfficialId(String animalId){
        if (animalId == null || "".equals(animalId)) return "";
        Animal animal = animalService.get(animalId);
        if (animal== null) return "";
        return animal.getOfficialId();
    }

}
