package com.farmogo.front;

import com.farmogo.model.AnimalCounter;
import com.farmogo.model.Farm;
import com.farmogo.services.FarmService;
import com.farmogo.services.GlobalSessionService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class ConfigView implements Serializable {

    @Inject
    FarmService farmService;

    private Farm farm;

    @PostConstruct
    public void init(){
        farm = farmService.getCurrentFarm();
    }

    public AnimalCounter getAnimalCounter() {
        return farm.getAnimalCounter();
    }

    public void setAnimalCounter(AnimalCounter animalCounter) {
        this.farm.setAnimalCounter(animalCounter);
    }

    public void save(){
        farmService.save(farm);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", farm.getAnimalCounter().toString()) );
    }
}
