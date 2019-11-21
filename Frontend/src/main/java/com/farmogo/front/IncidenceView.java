package com.farmogo.front;

import com.farmogo.model.incidences.GetoffType;
import com.farmogo.model.incidences.Incidence;
import com.farmogo.model.incidences.PregnancyType;
import com.farmogo.model.incidences.TreatmentType;
import com.farmogo.services.IncidencesService;

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

    List<Incidence> incidenceList;

    Incidence incidence;
    String animalId;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
        String animalId = (String) faceletContext.getAttribute("animalId");
        if (animalId == null) {
            Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
            animalId = params.get("animalId");
        }

        if (animalId != null) {
            incidenceList = incidencesService.getAll(animalId);
        } else {
            incidenceList = incidencesService.getAll();
        }
    }

    public String getAnimalId() {
        return this.animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
        incidenceList = incidencesService.getAll(animalId);
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

    public String formatDate(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public void save() {
        incidencesService.save(incidence);
    }

    public PregnancyType[] getPregnancyTypes() {
        return PregnancyType.values();
    }

    public TreatmentType[] getTreatmentTypes() {
        return TreatmentType.values();
    }

    public GetoffType[] getGetoffTypes() {
        return GetoffType.values();
    }


}
