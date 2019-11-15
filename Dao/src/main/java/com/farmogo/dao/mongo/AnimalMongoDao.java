package com.farmogo.dao.mongo;

import com.farmogo.dao.AnimalDao;
import com.farmogo.model.Animal;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class AnimalMongoDao implements AnimalDao {

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    private MongoCollection<Animal> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection("Animal", Animal.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public List<Animal> getAll() {
        return null;
    }

    @Override
    public void save(Animal animal) {

    }

    @Override
    public void delete(Animal animal) {

    }

    @Override
    public Animal get(String id) {
        return null;
    }
}
