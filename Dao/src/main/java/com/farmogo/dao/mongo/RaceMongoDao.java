package com.farmogo.dao.mongo;

import com.farmogo.dao.RaceDao;
import com.farmogo.dao.mongo.dto.RaceMongo;
import com.farmogo.model.Race;
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
public class RaceMongoDao implements RaceDao {

    public static final String COLLECTION = "Races";

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    MongoCollection<RaceMongo> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection("Race", RaceMongo.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public List<Race> getAll() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .map(RaceMongo::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Race save(Race race) {
        ObjectId key = null;
        if (race.getUuid() != null) {
            key = new ObjectId(race.getUuid());
        }
        if (key == null) {
            RaceMongo convert = RaceMongo.convert(race);
            convert.setUuid(new ObjectId());
            mongoCollection.insertOne(convert);
            return RaceMongo.convert(convert);
        } else {
            mongoCollection.replaceOne(Filters.eq("_id", key), RaceMongo.convert(race));
            return race;
        }

    }

    @Override
    public void delete(Race race) {
        mongoCollection.deleteOne(Filters.eq("_id", new ObjectId(race.getUuid())));
    }

    @Override
    public Race get(String id) {
        if (id == null) return null;
        ObjectId key = new ObjectId(id);
        return RaceMongo.convert(mongoCollection.find(Filters.eq("_id", key)).first());
    }
}
