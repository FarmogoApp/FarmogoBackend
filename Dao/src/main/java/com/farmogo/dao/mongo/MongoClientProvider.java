package com.farmogo.dao.mongo;


import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Arrays;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


@ApplicationScoped
public class MongoClientProvider {
    @Inject
    @ConfigProperty(name = "MONGO_URL", defaultValue = "")
    private String MONGO_URL;

    private MongoClient mongoClient;


    @PostConstruct
    public void init() {
        if (MONGO_URL.isEmpty()) {
            mongoClient = MongoClients.create();
        } else {
            mongoClient = MongoClients.create(MONGO_URL);
        }
    }

    @Produces
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    @Produces
    public MongoDatabase getMongoDatabase(){
        return mongoClient.getDatabase("FARMOGO");
    }

    @Produces
    public CodecRegistry  getCodecRegistry() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().
                conventions(Arrays.asList(Conventions.ANNOTATION_CONVENTION)).
                register(com.farmogo.dao.mongo.dto.AnimalTypeMongo.class).
                register(com.farmogo.dao.mongo.dto.IncidenceMongo.class).
                register(com.farmogo.dao.mongo.dto.IncidenceMongoPregnancy.class).
                register(com.farmogo.dao.mongo.dto.IncidenceMongoTreatment.class).
                register(com.farmogo.dao.mongo.dto.IncidenceMongoWeight.class).
                register(com.farmogo.dao.mongo.dto.IncidenceMongoGetoff.class).
                automatic(true).build();
        return fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    }
}
