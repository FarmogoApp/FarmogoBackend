package com.farmogo.front;

import com.farmogo.services.AnimalTypesService;
import com.farmogo.model.AnimalType;
import com.farmogo.services.HasRelationatedDataException;
import com.farmogo.services.NotificationService;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import static com.farmogo.services.NotificationService.ALL_TOPIC;

@Named
//@RequestScoped
@ViewScoped
public class AnimalTypeView implements Serializable {

    @Inject
    AnimalTypesService animalTypesService;

    @Inject
    NotificationService notificationService;

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
        sendPushNotification();
        init();

    }


    public void clearSelection(){
        animalType = new AnimalType();
    }

    public void save(){
        animalTypesService.save(animalType);
        sendPushNotification();
        Messages.info("AnimalType " + animalType.getDescription()+ " has been saved","");
        init();
    }

    public void delete(){
        try {
            animalTypesService.delete(animalType);
            sendPushNotification();
            Messages.info("AnimalType " + animalType.getDescription()+ " has been deleted","");
        }catch (HasRelationatedDataException ex){
            Messages.error("AnimalType is assigned to animal","If AnimalType is assigned tho animal you can't delete this");
        }
        init();
    }

    private void sendPushNotification(){
        try {
            notificationService.sendNotificationToTopic("Sync pending", "The animal types have been updated", ALL_TOPIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
