package com.farmogo.dao.mongo;

import com.farmogo.dao.AnimalDao;
import com.farmogo.dao.mongo.dto.AnimalMongoDTO;
import com.farmogo.model.Animal;
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
public class AnimalMongoDao implements AnimalDao {

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    private MongoCollection<AnimalMongoDTO> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection("Animal", AnimalMongoDTO.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public List<Animal> getAll() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .map(AnimalMongoDTO::getObject)
                .collect(Collectors.toList());
    }

    @Override
    public List<Animal> getAnimalsByFarmId(String farmId) {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .map(AnimalMongoDTO::getObject)
                .filter(p -> p.getFarmId().equals(farmId))
                .collect(Collectors.toList());
    }

    @Override
    public Animal save(Animal animal) {
        AnimalMongoDTO obj = mongoCollection.find(Filters.eq("_id", animal.getUuid())).first();

        if (obj == null) {
            mongoCollection.insertOne(new AnimalMongoDTO(animal));

        } else{
            mongoCollection.replaceOne(Filters.eq("_id", animal.getUuid()),new AnimalMongoDTO(animal));
        }
        return animal;
    }

    @Override
    public void delete(Animal animal) {
        mongoCollection.deleteOne(Filters.eq("_id", animal.getUuid()));
    }

    @Override
    public Animal get(String id) {
        return mongoCollection.find(Filters.eq("_id", id)).first().getObject();
    }
}
