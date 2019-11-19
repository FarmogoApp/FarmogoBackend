package com.farmogo.dao.mongo;

import com.farmogo.dao.AnimalTypeDao;
import com.farmogo.dao.mongo.dto.AnimalTypeMongo;
import com.farmogo.model.AnimalType;
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

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;
import static jdk.nashorn.internal.objects.NativeFunction.function;

@Stateless
public class AnimalTypeMongoDao implements AnimalTypeDao {

    public static final String COLLECTION = "AnimalTypes";
    @Inject
    CodecRegistry codecRegistry;

    @Inject
    MongoDatabase mongoDatabase;

    MongoCollection<AnimalTypeMongo> mongoCollection;

    @PostConstruct
    public void init() {
        mongoCollection = mongoDatabase.getCollection(COLLECTION, AnimalTypeMongo.class).withCodecRegistry(codecRegistry);
    }

    public AnimalType get(String id) {
        if (id == null) return null;
        ObjectId key = new ObjectId(id);
        return AnimalTypeMongo.convert(mongoCollection.find(Filters.eq("_id", key)).first());

    }


    public List<AnimalType> getAll() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .map(AnimalTypeMongo::convert)
                .collect(Collectors.toList());
    }

    public AnimalType save(AnimalType animalType) {
        ObjectId key = null;
        if (animalType.getAnimalType() != null) {
            key = new ObjectId(animalType.getAnimalType());
        }
        if (key == null) {

            AnimalTypeMongo convert = AnimalTypeMongo.convert(animalType);
            convert.setAnimalType(new ObjectId());
            mongoCollection.insertOne(convert);
            return AnimalTypeMongo.convert(convert);
        } else {
            mongoCollection.replaceOne(Filters.eq("_id", key), AnimalTypeMongo.convert(animalType));
            return animalType;
        }
    }

    public void delete(AnimalType animalType) {
        if (animalType.getAnimalType() != null) {
            ObjectId key = new ObjectId(animalType.getAnimalType());
            mongoCollection.deleteOne(Filters.eq("_id", key));
        }
    }


}
