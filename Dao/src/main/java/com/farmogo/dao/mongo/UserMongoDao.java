package com.farmogo.dao.mongo;

import com.farmogo.dao.UserDao;
import com.farmogo.dao.mongo.dto.UserMongo;
import com.farmogo.model.User;
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
public class UserMongoDao implements UserDao {

    public static final String COLLECTION = "Users";

    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    MongoCollection<UserMongo> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection(COLLECTION, UserMongo.class).withCodecRegistry(codecRegistry);
    }

    @Override
    public List<User> getAll() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .map(UserMongo::convert)
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        ObjectId key = null;
        if (user.getUuid() != null) {
            key = new ObjectId(user.getUuid());
        }
        if (key == null) {
            UserMongo convert = UserMongo.convert(user);
            convert.setUuid(new ObjectId());
            mongoCollection.insertOne(convert);
            return UserMongo.convert(convert);
        } else {
            mongoCollection.replaceOne(Filters.eq("_id", key), UserMongo.convert(user));
            return user;
        }

    }

    @Override
    public void delete(User user) {
        mongoCollection.deleteOne(Filters.eq("_id", new ObjectId(user.getUuid())));
    }

    @Override
    public User get(String id) {
        if (id == null) return null;
        ObjectId key = new ObjectId(id);
        return UserMongo.convert(mongoCollection.find(Filters.eq("_id", key)).first());
    }

    @Override
    public User getByFirebaseUuid(String firebaseUuid) {
        if (firebaseUuid == null) return null;
        return UserMongo.convert(mongoCollection.find(Filters.eq("firebaseUuid", firebaseUuid)).first());
    }
}
