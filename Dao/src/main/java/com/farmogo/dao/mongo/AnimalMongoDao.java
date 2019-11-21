package com.farmogo.dao.mongo;

import com.farmogo.dao.AnimalDao;
import com.farmogo.dao.mongo.dto.AnimalMongo;
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

    public static final String COLLECTION = "Animals";

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    private MongoCollection<AnimalMongo> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection(COLLECTION, AnimalMongo.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public List<Animal> getAll() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .map(AnimalMongo::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<Animal> getAnimalsByFarmId(String farmId) {
        return StreamSupport.stream(mongoCollection.find()
                .filter(Filters.eq("farmId", new ObjectId(farmId))).spliterator(), false)
                .map(AnimalMongo::convert)
                .collect(Collectors.toList());

    }

    @Override
    public Animal save(Animal animal) {

        ObjectId key = null;
        if(animal.getUuid() != null){
            key = new ObjectId(animal.getUuid());
        }
        if (key == null){
            AnimalMongo convert = AnimalMongo.convert(animal);
            convert.setUuid(new ObjectId());
            animal.setUuid(convert.getUuid().toString());
            mongoCollection.insertOne(convert);
        } else {
            mongoCollection.replaceOne(Filters.eq("_id", key), AnimalMongo.convert(animal));
        }
        return animal;
    }

    @Override
    public void delete(Animal animal) {
        mongoCollection.deleteOne(Filters.eq("_id", new ObjectId(animal.getUuid())));
    }

    @Override
    public Animal get(String id) {
        return AnimalMongo.convert(mongoCollection.find(Filters.eq("_id", new ObjectId(id))).first());
    }
}
