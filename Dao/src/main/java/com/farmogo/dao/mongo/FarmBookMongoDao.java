package com.farmogo.dao.mongo;

import com.farmogo.dao.FarmBookDao;
import com.farmogo.dao.mongo.dto.FarmBookMongo;
import com.farmogo.model.FarmBook;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class FarmBookMongoDao implements FarmBookDao {

    public static final String COLLECTION = "FarmBook";

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    private MongoCollection<FarmBookMongo> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection(COLLECTION, FarmBookMongo.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public List<FarmBook> getAll() {
        return null;
    }

    @Override
    public FarmBook save(FarmBook farmBook) {
        return null;
    }

    @Override
    public void delete(FarmBook farmBook) {

    }

    @Override
    public FarmBook get(String id) {
        return null;
    }
}
