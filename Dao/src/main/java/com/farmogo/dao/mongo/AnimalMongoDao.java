package com.farmogo.dao.mongo;

import com.farmogo.dao.AnimalDao;
import com.farmogo.dao.mongo.dto.AnimalMongoDTO;
import com.farmogo.dao.mongo.dto.IncidenceMongo;
import com.farmogo.model.Animal;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

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
                .map(AnimalMongoDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<Animal> getAnimalsByFarmId(String farmId) {
        return StreamSupport.stream(mongoCollection.find()
                .filter(Filters.eq("farmId", new ObjectId(farmId))).spliterator(), false)
                .map(AnimalMongoDTO::convert)
                .collect(Collectors.toList());

    }

    @Override
    public Animal save(Animal animal) {

        ObjectId key = null;
        if(animal.getUuid() != null){
            key = new ObjectId(animal.getUuid());
        }
        if (key == null){
            AnimalMongoDTO convert = AnimalMongoDTO.convert(animal);
            convert.setUuid(new ObjectId());
            animal.setUuid(convert.getUuid().toString());
            mongoCollection.insertOne(convert);
        } else {
            mongoCollection.replaceOne(Filters.eq("_id", key), AnimalMongoDTO.convert(animal));
        }
        return animal;
    }

    @Override
    public void delete(Animal animal) {
        mongoCollection.deleteOne(Filters.eq("_id", new ObjectId(animal.getUuid())));
    }

    @Override
    public Animal get(String id) {
        return AnimalMongoDTO.convert(mongoCollection.find(Filters.eq("_id", id)).first());
    }
}
