package com.farmogo.front;

import com.farmogo.services.AnimalTypesService;
import com.farmono.model.AnimalType;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
//@RequestScoped
@ViewScoped
public class AnimalTypeView implements Serializable {

    @Inject
    AnimalTypesService animalTypesService;

    private List<AnimalType> animalTypeList;

    @PostConstruct
    public void init() {
        animalTypeList = animalTypesService.getAll();
    }

    public List<AnimalType> getAnimalTypeList() {
        return animalTypeList;
    }

    public void setAnimalTypeList(List<AnimalType> animalTypeList) {
        this.animalTypeList = animalTypeList;
    }

    public void onRowEdit(RowEditEvent event) {
        animalTypesService.save((AnimalType) event.getObject());
        //animalTypeList.remove((AnimalType)event.getSource());
        //animalTypeList.add((AnimalType)event.getObject());
    }

}
