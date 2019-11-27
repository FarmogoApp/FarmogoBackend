package com.farmogo.dao.mongo;

import com.farmogo.dao.MarketHistoryDao;
import com.farmogo.dao.mongo.dto.MarketHistoryMongo;
import com.farmogo.model.MarketHistory;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Stateless
public class MarketHistoryMongoDao implements MarketHistoryDao {

    public static final String COLLECTION = "MarketHistory";

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    MongoCollection<MarketHistoryMongo> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection(COLLECTION, MarketHistoryMongo.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public List<MarketHistory> getAll() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .map(MarketHistoryMongo::convert)
                .collect(Collectors.toList());
    }

    @Override
    public MarketHistory save(MarketHistory marketHistory) {
        return null;
    }

}
