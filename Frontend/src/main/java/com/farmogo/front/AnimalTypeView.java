package com.farmogo.front;

import com.farmogo.services.AnimalTypesService;
import com.farmono.model.AnimalType;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
//@RequestScoped
@ViewScoped
public class AnimalTypeView implements Serializable {

    @Inject
    AnimalTypesService animalTypesService;

    private List<AnimalType> animalTypeList;

    private AnimalType animalType;

    @PostConstruct
    public void init() {
        animalTypeList = animalTypesService.getAll();
        animalType = new AnimalType();
    }

    public List<AnimalType> getAnimalTypeList() {
        return animalTypeList;
    }

    public void setAnimalTypeList(List<AnimalType> animalTypeList) {
        this.animalTypeList = animalTypeList;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public void onRowEdit(RowEditEvent event) {
        animalTypesService.save((AnimalType) event.getObject());
    }

    public void newAnimalType() {
        System.out.println("new animal");
        animalType = new AnimalType();
        Map<String,Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
    }

    public void save(){
        animalTypesService.save(animalType);
    }

}
