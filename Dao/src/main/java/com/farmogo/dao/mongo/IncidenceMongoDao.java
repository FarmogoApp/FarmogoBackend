package com.farmogo.dao.mongo;

import com.farmogo.dao.IncidenceDao;
import com.farmogo.dao.mongo.dto.IncidenceMongo;
import com.farmogo.model.incidences.Incidence;
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

import static com.mongodb.client.model.Sorts.*;


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
        ObjectId key = null;
        if(incidence.getUuid() != null){
            key = new ObjectId(incidence.getUuid());
        }
        if (key == null){
            mongoCollection.insertOne(IncidenceMongo.convert(incidence));
        } else {
            mongoCollection.replaceOne(Filters.eq("_id", key), IncidenceMongo.convert(incidence));
        }
    }

    @Override
    public List<Incidence> getAll() {
        return StreamSupport.stream(mongoCollection.find()
                .sort(orderBy(descending("created")))
                .spliterator(), false)
                .map(IncidenceMongo::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<Incidence> getAll(String animalid) {
        return StreamSupport.stream(
                mongoCollection.find()
                        .filter(Filters.eq("animalId", new ObjectId(animalid)))
                        .sort(orderBy(descending("date")))
                        .spliterator(), false)
                .map(IncidenceMongo::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<Incidence> getNotCompleted(String farmId) {
        return StreamSupport.stream(
                mongoCollection.find()
                        .filter(Filters.and(
                                Filters.eq("farmId", new ObjectId(farmId)),
                                Filters.eq("complete", false)
                        ))
                        .sort(orderBy(descending("date")))
                        .spliterator(), false)

                .map(IncidenceMongo::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<Incidence> getLast(String farmId, int limit) {
        return StreamSupport.stream(
                mongoCollection.find()
                        .filter(
                                Filters.eq("farmId", new ObjectId(farmId))
                        )
                        .sort(orderBy(descending("date")))
                        .limit(limit)
                        .spliterator(), false)

                .map(IncidenceMongo::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<Incidence> getByAnimalId(String animalId, int skip, int limit) {
        return StreamSupport.stream(
                mongoCollection.find()
                        .filter(
                                Filters.eq("animalId", new ObjectId(animalId))
                        )
                        .sort(orderBy(descending("date")))
                        .skip(skip)
                        .limit(limit)
                        .spliterator(), false)

                .map(IncidenceMongo::convert)
                .collect(Collectors.toList());
    }


}