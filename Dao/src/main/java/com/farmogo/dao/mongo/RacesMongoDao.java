package com.farmogo.dao.mongo;

import com.farmogo.dao.RacesDao;
import com.farmogo.model.Races;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RacesMongoDao implements RacesDao {

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    private MongoCollection<Races> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection("Races", Races.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public List<Races> getAll() {
        return null;
    }

    @Override
    public void save(Races race) {

    }

    @Override
    public void delete(Races race) {

    }

    @Override
    public Races get(String id) {
        return null;
    }
}
