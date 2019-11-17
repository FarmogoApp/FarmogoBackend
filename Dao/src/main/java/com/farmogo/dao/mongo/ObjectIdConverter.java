package com.farmogo.dao.mongo;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.bson.types.ObjectId;

public class ObjectIdConverter extends BidirectionalConverter<ObjectId, String> {
    @Override
    public String convertTo(ObjectId objectId, Type<String> type, MappingContext mappingContext) {
        if (objectId == null) return null;
        return objectId.toString();
    }

    @Override
    public ObjectId convertFrom(String s, Type<ObjectId> type, MappingContext mappingContext) {
        if (s==null) return null;
        return new ObjectId(s);
    }
}
