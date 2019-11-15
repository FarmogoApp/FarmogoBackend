package com.farmogo.dao.mongo;

import com.farmogo.dao.BuildingDao;
import com.farmogo.model.Building;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class BuildingMongoDao implements BuildingDao {

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    private MongoCollection<Building> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection("Building", Building.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public List<Building> getAll() {
        return null;
    }

    @Override
    public void save(Building building) {

    }

    @Override
    public void delete(Building building) {

    }

    @Override
    public Building get(String id) {
        return null;
    }
}
