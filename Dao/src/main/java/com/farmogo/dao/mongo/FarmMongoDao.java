package com.farmogo.dao.mongo;

import com.farmogo.dao.FarmDao;
import com.farmogo.dao.mongo.dto.AnimalTypeMongo;
import com.farmogo.dao.mongo.dto.FarmMongo;
import com.farmogo.model.Farm;
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
public class FarmMongoDao implements FarmDao {
    public static final String COLLECTION = "Farms";

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    MongoCollection<FarmMongo> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection(COLLECTION, FarmMongo.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public List<Farm> getFarmByUser(String userId) {
        return null;
    }

    @Override
    public List<Farm> getAll() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .map(FarmMongo::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Farm get(String id) {
        return null;
    }

    @Override
    public void save(Farm farm) {
        ObjectId key = null;
        if (farm.getUuid() != null) {
            key = new ObjectId(farm.getUuid());
        }
        if (key == null) {
            mongoCollection.insertOne(FarmMongo.convert(farm));
        } else {
            mongoCollection.replaceOne(Filters.eq("_id", key), FarmMongo.convert(farm));
        }
    }
}
