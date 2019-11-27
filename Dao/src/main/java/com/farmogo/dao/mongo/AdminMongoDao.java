package com.farmogo.dao.mongo;

import com.farmogo.dao.AdminDao;
import com.mongodb.client.MongoDatabase;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AdminMongoDao implements AdminDao {

    @Inject
    MongoDatabase mongoDatabase;

    @Override
    public void clearDatabase() {
        mongoDatabase.drop();

    }
}
