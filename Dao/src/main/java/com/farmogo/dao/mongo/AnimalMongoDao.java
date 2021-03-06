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
import java.util.Objects;
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
    public List<Animal> getAll(List<String> farmsId) {
        Objects.requireNonNull(farmsId);
        List<ObjectId> farms = farmsId.stream().map(ObjectId::new).collect(Collectors.toList());
        return StreamSupport.stream(mongoCollection.find(Filters.in("farmId", farms)).spliterator(), false)
                .map(AnimalMongo::convert)
                .collect(Collectors.toList());
    }


    @Override
    public List<Animal> getCurrentAnimalsByFarmId(String farmId) {
        return StreamSupport.stream(mongoCollection.find()
                .filter(Filters.and(
                        Filters.eq("farmId", new ObjectId(farmId)),
                        Filters.not(Filters.exists("dischargeDate"))
                )).spliterator(), false)
                .map(AnimalMongo::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<Animal> getCurrentAnimals() {
        return StreamSupport.stream(mongoCollection.find()
                .filter(Filters.not(Filters.exists("dischargeDate")))
                .spliterator(), false)
                .map(AnimalMongo::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<Animal> getAllAnimalsByFarmId(String farmId) {
        return StreamSupport.stream(mongoCollection.find()
                .filter(Filters.eq("farmId", new ObjectId(farmId))).spliterator(), false)
                .map(AnimalMongo::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<Animal> getDischargedAnimalsByFarmId(String farmId) {
        List<Animal> collect = StreamSupport.stream(mongoCollection.find()
                .filter(Filters.and(
                        Filters.eq("farmId", new ObjectId(farmId)),
                        Filters.exists("dischargeDate")
                )).spliterator(), false)
                .map(AnimalMongo::convert)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public Animal save(Animal animal) {

        ObjectId key = null;
        if (animal.getUuid() != null) {
            key = new ObjectId(animal.getUuid());
        }
        if (key == null) {
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
        if (id == null) return null;
        return AnimalMongo.convert(mongoCollection.find(Filters.eq("_id", new ObjectId(id))).first());
    }

    @Override
    public List<Animal> getByRace(String raceId) {
        List<Animal> collect = StreamSupport.stream(mongoCollection.find()
                .filter(Filters.eq("raceId", new ObjectId(raceId)))
                .spliterator(), false)
                .map(AnimalMongo::convert)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Animal> getByAnimalType(String animalTypeId) {
        List<Animal> collect = StreamSupport.stream(mongoCollection.find()
                .filter(Filters.eq("animalTypeId", new ObjectId(animalTypeId)))
                .spliterator(), false)
                .map(AnimalMongo::convert)
                .collect(Collectors.toList());
        return collect;
    }


}
