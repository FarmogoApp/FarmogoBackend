package com.farmogo.front;

import com.farmogo.services.AnimalTypesService;
import com.farmogo.model.AnimalType;
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
        init();
    }


    public void clearSelection(){
        animalType = new AnimalType();
    }

    public void save(){
        animalTypesService.save(animalType);
        init();
    }

    public void delete(){

        animalTypesService.delete(animalType);
        init();
    }

}
