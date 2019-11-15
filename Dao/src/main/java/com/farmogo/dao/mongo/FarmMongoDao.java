package com.farmogo.dao.mongo;

import com.farmogo.dao.FarmDao;
import com.farmogo.model.Farm;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class FarmMongoDao implements FarmDao {

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    private MongoCollection<Farm> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection("Farm", Farm.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public List<Farm> getAll() {
        return null;
    }

    @Override
    public void save(Farm farm) {

    }

    @Override
    public void delete(Farm farm) {

    }

    @Override
    public Farm get(String id) {
        return null;
    }
}
