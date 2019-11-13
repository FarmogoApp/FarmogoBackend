package com.farmogo.dao.mongo;

import com.farmogo.dao.AnimalTypeDao;
import com.farmogo.dao.mongo.dto.AnimalTypeMongo;
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

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    MongoCollection<AnimalTypeMongo> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection("AnimalTypes", AnimalTypeMongo.class).withCodecRegistry(codecRegistry);
    }

    public AnimalType get(String id) {
        return mongoCollection.find(Filters.eq("_id", id)).first().getObject();
    }


    public List<AnimalType> getAll() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .map(AnimalTypeMongo::getObject)
                .collect(Collectors.toList());
    }

    public void save(AnimalType animalType) {
        AnimalTypeMongo obj = mongoCollection.find(Filters.eq("_id", animalType.getAnimalType())).first();
        if (obj == null) {
            mongoCollection.insertOne(new AnimalTypeMongo(animalType));
        }else{
            mongoCollection.replaceOne(Filters.eq("_id", animalType.getAnimalType()),new AnimalTypeMongo(animalType));
        }
    }

    public void delete(AnimalType animalType) {
        mongoCollection.deleteOne(Filters.eq("_id", animalType.getAnimalType()));
    }
}
