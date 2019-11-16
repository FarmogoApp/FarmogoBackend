package com.farmogo.dao.mongo;

import com.farmogo.dao.IncidenceDao;
import com.farmogo.dao.mongo.dto.IncidenceMongo;
import com.farmogo.dao.mongo.dto.Mapper;
import com.farmogo.model.incidences.Incidence;
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
public class IncidenceMongoDao implements IncidenceDao {


    public static final String COLLECTION = "Incidences";
    @Inject
    CodecRegistry codecRegistry;
    @Inject
    MongoDatabase mongoDatabase;
    MongoCollection<IncidenceMongo> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection(COLLECTION, IncidenceMongo.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public void save(Incidence incidence) {
        IncidenceMongo obj = mongoCollection.find(Filters.eq("_id", incidence.getUuid())).first();
        if (obj == null) {
            mongoCollection.insertOne(IncidenceMongo.convert(incidence));
        } else {
            mongoCollection.replaceOne(Filters.eq("_id", incidence.getUuid()), IncidenceMongo.convert(incidence));
        }
    }

    @Override
    public List<Incidence> getAll() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .map(IncidenceMongo::convert)
                .collect(Collectors.toList());
    }


}