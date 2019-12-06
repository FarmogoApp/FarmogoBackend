package com.farmogo.dao.mongo;

import com.farmogo.dao.FarmDao;
import com.farmogo.dao.mongo.dto.FarmMongo;
import com.farmogo.model.Farm;
import com.mongodb.client.FindIterable;
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
    public List<Farm> getFarmByOwner(String userId) {
        if (userId == null) return null;
        ObjectId userOwnerId = new ObjectId(userId);
        FindIterable<FarmMongo> findIterable = mongoCollection.find(Filters.eq("userOwnerId", userOwnerId));
        return StreamSupport.stream(findIterable.spliterator(), false)
                .map(FarmMongo::convert)
                .collect(Collectors.toList());

    }

    @Override
    public List<Farm> getAll() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .map(FarmMongo::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Farm get(String id) {
        if (id == null) return null;
        ObjectId key = new ObjectId(id);
        return FarmMongo.convert(mongoCollection.find(Filters.eq("_id", key)).first());
    }

    @Override
    public Farm save(Farm farm) {
        FarmMongo convert = FarmMongo.convert(farm);
        fillIds(convert);

        ObjectId key = null;
        if (farm.getUuid() != null) {
            key = new ObjectId(farm.getUuid());
        }
        if (key == null) {
            mongoCollection.insertOne(convert);
        } else {
            mongoCollection.replaceOne(Filters.eq("_id", key), convert);
        }
        return FarmMongo.convert(convert);
    }

    private void fillIds(FarmMongo convert) {
        if (convert.getUuid() == null) convert.setUuid(new ObjectId());
        if (convert.getBuildings() != null) {
            convert.getBuildings().forEach(b -> {
                if (b.getUuid() == null) b.setUuid(new ObjectId());
                if (b.getDivisions() != null) {
                    b.getDivisions().forEach(d -> {
                        if (d.getUuid() == null)
                            d.setUuid(new ObjectId());
                    });
                }
            });
        }
    }

    public void delete(Farm farm) {
        if (farm.getUuid() != null) {
            ObjectId key = new ObjectId(farm.getUuid());
            mongoCollection.deleteOne(Filters.eq("_id", key));
        }
    }
}
