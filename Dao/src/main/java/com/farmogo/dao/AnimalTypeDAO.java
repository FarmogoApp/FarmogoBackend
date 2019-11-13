package com.farmogo.dao;

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
public class AnimalTypeDAO {

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    MongoCollection<AnimalType> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection("AnimalTypes", AnimalType.class).withCodecRegistry(codecRegistry);
    }

    public AnimalType get(String id) {
        return mongoCollection.find(Filters.eq("_id", id)).first();
    }


    public List<AnimalType> getAll() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false).collect(Collectors.toList());
    }

    public void save(AnimalType animalType) {
        AnimalType obj = get(animalType.getAnimalType());
        if (obj == null) {
            mongoCollection.insertOne(animalType);
        }else{
            mongoCollection.replaceOne(Filters.eq("_id", animalType.getAnimalType()),animalType);
        }
    }

    public void delete(AnimalType animalType) {
        mongoCollection.deleteOne(Filters.eq("_id", animalType.getAnimalType()));
    }
}
