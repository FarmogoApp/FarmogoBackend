package com.farmogo.dataAccess;

import static java.lang.String.format;

import com.farmono.model.BaseMongoDO;
import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MongoConnection {

    private static Logger logger = Logger.getLogger(MongoConnection.class);
    private static MongoConnection instance = new MongoConnection();

    private MongoClient mongo = null;
    private Datastore dataStore = null;
    private Morphia morphia = null;
    private Properties config = null;

    private MongoConnection() {}

    public MongoClient getMongo() throws RuntimeException {
        if (mongo == null) {
            if(config == null) getMongoConfiguration();
            logger.debug("Starting Mongo");
            MongoClientOptions.Builder options = MongoClientOptions.builder()
                    .connectionsPerHost(4)
                    .maxConnectionIdleTime((60 * 1_000))
                    .maxConnectionLifeTime((120 * 1_000));

            // TODO: Use a config file
            MongoClientURI uri = new MongoClientURI("mongodb://" + config.getProperty("host") +  ":" + config.getProperty("port") + "/", options);

            logger.info("About to connect to MongoDB @ " + uri.toString());

            try {
                mongo = new MongoClient(uri);
                mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
            } catch (MongoException ex) {
                logger.error("An error occoured when connecting to MongoDB", ex);
            } catch (Exception ex) {
                logger.error("An error occoured when connecting to MongoDB", ex);
            }

            // To be able to wait for confirmation after writing on the DB
            mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        }

        return mongo;
    }

    public Morphia getMorphia() {
        if (morphia == null) {
            logger.debug("Starting Morphia");
            morphia = new Morphia();

            logger.debug(format("Mapping packages for clases within %s", BaseMongoDO.class.getName()));
            morphia.mapPackageFromClass(BaseMongoDO.class);
        }

        return morphia;
    }

    public Datastore getDatastore() {
        if (dataStore == null) {
            if(config == null) getMongoConfiguration();
            logger.debug(format("Starting DataStore on DB: %s", config.getProperty("dbName")));
            dataStore = getMorphia().createDatastore(getMongo(), config.getProperty("dbName"));
        }

        return dataStore;
    }

    private void getMongoConfiguration(){

        try (InputStream input = MongoConnection.class.getClassLoader().getResourceAsStream("config.properties")) {

            config = new Properties();

            if (input == null) {
                logger.info("Config file not found");
                return;
            }
            logger.info("MongoDB config found");
            logger.info("host : " + config.getProperty("host"));
            logger.info("port : " + config.getProperty("port"));
            logger.info("dbName : " + config.getProperty("dbName"));


            config.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void init() {
        logger.info("Init MongoConnection");
        getMongoConfiguration();
        getMongo();
        getMorphia();
        getDatastore();
    }

    public void close() {
        logger.info("Closing MongoDB connection");
        if (mongo != null) {
            try {
                mongo.close();
                logger.debug("Nulling the connection dependency objects");
                mongo = null;
                morphia = null;
                dataStore = null;
            } catch (Exception e) {
                logger.error(format("An error occurred when closing the MongoDB connection\n%s", e.getMessage()));
            }
        } else {
            logger.warn("mongo object was null, wouldn't close connection");
        }
    }

    public static MongoConnection getInstance() {
        return instance;
    }
}