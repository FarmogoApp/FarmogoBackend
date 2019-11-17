package com.farmogo.dao.mongo;

import com.farmogo.dao.AnimalTypeDao;
import com.farmogo.dao.mongo.dto.AnimalTypeMongo;
import com.farmogo.dao.mongo.dto.Mapper;
import com.farmogo.model.AnimalType;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecRegistry;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Stateless
public class AnimalTypeMongoDao implements AnimalTypeDao {

    public static final String COLLECTION = "AnimalTypes";
    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    MongoCollection<AnimalTypeMongo> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection(COLLECTION, AnimalTypeMongo.class).withCodecRegistry(codecRegistry);
    }

    public AnimalType get(String id) {
      return convert(mongoCollection.find(Filters.eq("_id", id)).first());
    }


    public List<AnimalType> getAll() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .map(AnimalTypeMongoDao::convert)
                .collect(Collectors.toList());
    }

    public void save(AnimalType animalType) {
        AnimalTypeMongo obj = mongoCollection.find(Filters.eq("_id", animalType.getAnimalType())).first();
        if (obj == null) {
            mongoCollection.insertOne(convert(animalType));
        }else{
            mongoCollection.replaceOne(Filters.eq("_id", animalType.getAnimalType()),convert(animalType));
        }
    }

    public void delete(AnimalType animalType) {
        mongoCollection.deleteOne(Filters.eq("_id", animalType.getAnimalType()));
    }

    public static AnimalTypeMongo convert(AnimalType incidence){
        if (incidence == null) return null;
        return Mapper.getInstance().map(incidence, AnimalTypeMongo.class);
    }

    public static AnimalType convert(AnimalTypeMongo incidenceMongo){
        if (incidenceMongo == null) return null;
        return Mapper.getInstance().map(incidenceMongo, AnimalType.class);
    }
}
