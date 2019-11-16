package com.farmogo.front;

import com.farmogo.model.Animal;
import com.farmogo.model.incidences.Incidence;
import com.farmogo.model.incidences.IncidenceType;
import com.farmogo.services.IncidencesService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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

    @PostConstruct
    public void init() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String,String> params =
                facesContext.getExternalContext().getRequestParameterMap();
        if (params.containsKey("animal")){
            Animal animal = new Animal();
            animal.setUuid(params.get("animal"));
            incidenceList = incidencesService.getAll(animal);
        }else{
            incidenceList = incidencesService.getAll();
        }


        String action = params.get("animalId");

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

    public IncidenceType[] getTypes() {
        return IncidenceType.values();
    }
}
