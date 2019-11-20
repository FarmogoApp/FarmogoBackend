package com.farmogo.dao.mongo;

import com.farmogo.dao.RaceDao;
import com.farmogo.dao.mongo.dto.RaceMongoDTO;
import com.farmogo.model.Race;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecRegistry;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Stateless
public class RaceMongoDao implements RaceDao {

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    private MongoCollection<RaceMongoDTO> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection("Race", RaceMongoDTO.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public List<Race> getAll() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .map(RaceMongoDTO::getObject)
                .collect(Collectors.toList());
    }

    @Override
    public Race save(Race race) {
        RaceMongoDTO obj = mongoCollection.find(Filters.eq("_id", race.getUuid())).first();

        if (obj == null) {
            mongoCollection.insertOne(new RaceMongoDTO(race));

        } else{
            mongoCollection.replaceOne(Filters.eq("_id", race.getUuid()),new RaceMongoDTO(race));
        }
        return race;
    }

    @Override
    public void delete(Race race) {
        mongoCollection.deleteOne(Filters.eq("_id", race.getUuid()));
    }

    @Override
    public Race get(String id) {
        return mongoCollection.find(Filters.eq("_id", id)).first().getObject();
    }
}
