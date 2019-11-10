package com.farmogo.services;

import com.farmogo.dao.AnimalTypeDAO;
import com.farmogo.dataAccess.MongoConnection;
import com.farmono.model.AnimalType;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import javax.ejb.Stateless;
import java.util.List;


@Stateless
public class AnimalTypesService {

    private MongoConnection conn = MongoConnection.getInstance();
    private AnimalTypeDAO animalTypeDAO = new AnimalTypeDAO(conn.getDatastore());

    public List<AnimalType> getAll() {
        return animalTypeDAO.createQuery().asList();
    }

    public void addAnimalType(AnimalType animalType) {
        animalTypeDAO.save(animalType);
    }

    public AnimalType getAnimalTypeByAnimalType(int animalType){
        return animalTypeDAO.createQuery().filter("animalType", animalType).asList().get(0);
    }

    public void removeAnimalType(int animalType){
        Query<AnimalType> query = animalTypeDAO.createQuery().filter("animalType", animalType);
        animalTypeDAO.deleteByQuery(query);
    }

    public void updateAnimalType(int animalType, AnimalType updatedAnimalType){
        Query<AnimalType> query = animalTypeDAO.createQuery().filter("animalType", animalType);
        UpdateOperations<AnimalType> updateOperations = animalTypeDAO.createUpdateOperations()
                                                                        .set("description", updatedAnimalType.getDescription() )
                                                                        .set("icon", updatedAnimalType.getIcon());
        animalTypeDAO.update(query, updateOperations);
    }
}
