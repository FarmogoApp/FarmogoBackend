package com.farmogo.front;


import com.farmogo.model.AccessNotAllowed;
import com.farmogo.model.Animal;
import com.farmogo.model.PermissionError;
import com.farmogo.model.Race;
import com.farmogo.model.incidences.*;
import com.farmogo.services.*;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Named
@ViewScoped
@ManagedBean
public class IncidenceView implements Serializable {

    @Inject
    RaceService raceService;

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
    private Map<String,String> races = new HashMap<>();
    IncidenceType incidenceType;
    String title;
    PropertyResourceBundle i18n;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
        animalId = (String) faceletContext.getAttribute("animalId");
        i18n = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{i18n}", PropertyResourceBundle.class);

        if (animalId == null) {
            Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
            animalId = params.get("animalId");
        }

        HashRaces();
        updateIncidenceList();
    }

    private void updateIncidenceList() {
        try {
            if (animalId != null) {
                incidenceList = incidencesService.getAll(animalId);
                Animal animal = null;

                animal = animalService.get(animalId);

                title = i18n.getString("incidences.incidences") + " - " + animal.getOfficialId();
            } else {
                if (farmService.getCurrentFarm() == null) {
                    incidenceList = Collections.emptyList();
                } else {
                    incidenceList = incidencesService.getNotCompleted(farmService.getCurrentFarm().getUuid());
                }
                title = i18n.getString("menu.incompletedIncidences");
            }
        } catch (AccessNotAllowed accessNotAllowed) {

            Messages.error("Not allowed to get this information", "");
        }
    }

    public String getTitle() {
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
                break;
            case BIRTH:
                IncidenceBirth i = new IncidenceBirth();
                i.setChildOfficialId(farmService.getCurrentFarm().getAnimalCounter().toString());
                incidence = i;
                break;

        }
        incidence.setAnimalId(animalId);
        incidence.setCreatedBy(userService.getCurrentUser().getUuid());
    }

    private void HashRaces() {

        races = raceService.getAll().stream()
                .collect(Collectors.toMap(Race::getUuid, Race::getName));
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

    public void save() {
        try {
            incidencesService.save(incidence);
            updateIncidenceList();
            animalDataView.updateAnimal(animalService.get(incidence.getAnimalId()));
            Messages.info("Incidence has been saved", "");
        } catch (PermissionError accessNotAllowed) {
            Messages.error("Not alloed to save", "");
        }

    }

    public void remove() {
        incidence.setRemoveDate(LocalDate.now());
        try {
            incidencesService.save(incidence);
            updateIncidenceList();
            Messages.info("Incidence has been removed", "");
        } catch (PermissionError accessNotAllowed) {
            Messages.error("Not alloed to remove", "");
        }

    }

    public void recover(Incidence incidence) {
        try {
            this.incidence = incidence;
            this.incidence.setRemoveDate(null);
            this.incidence.setRemoveReason(null);
            incidencesService.save(incidence);
            updateIncidenceList();
            Messages.info("Incidence has been recovered", "");
        } catch (PermissionError accessNotAllowed) {
            Messages.error("Not alloed to recover", "");
        }

    }

    public IncidenceType[] getIncidenceTypes() {
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

    public String getAnimalOfficialId(String animalId) {
        try {
            if (animalId == null || "".equals(animalId)) return "";
            Animal animal = null;
            animal = animalService.get(animalId);
            if (animal == null) return "";
            return animal.getOfficialId();
        } catch (AccessNotAllowed accessNotAllowed) {
            Messages.error("Not alloed to get this information", "");
        }
        return "";
    }

    public Map<String, String> getRaces() {
        return races;
    }

    public void setRaces(Map<String, String> races) {
        this.races = races;
    }
}
