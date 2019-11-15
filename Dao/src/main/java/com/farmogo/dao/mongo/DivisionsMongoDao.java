package com.farmogo.dao.mongo;

import com.farmogo.dao.DivisionsDao;
import com.farmogo.model.Divisions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DivisionsMongoDao implements DivisionsDao {

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    private MongoCollection<Divisions> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection("Divisions", Divisions.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public List<Divisions> getAll() {
        return null;
    }

    @Override
    public void save(Divisions division) {

    }

    @Override
    public void delete(Divisions division) {

    }

    @Override
    public Divisions get(String id) {
        return null;
    }
}
