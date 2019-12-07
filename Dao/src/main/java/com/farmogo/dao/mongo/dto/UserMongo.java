package com.farmogo.dao.mongo.dto;

import com.farmogo.model.User;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.List;

public class UserMongo {

    @BsonId
    private ObjectId uuid;
    private String email;
    private String name;
    private String telephone;
    private List<ObjectId> farmsAccessible;
    private String firebaseUuid;
    private List<String> firebaseNotificationTokens;

    public static UserMongo convert(User user) {
        return Mapper.getInstance().map(user, UserMongo.class);
    }

    public static User convert(UserMongo userMongo) {
        return Mapper.getInstance().map(userMongo, User.class);
    }

    public ObjectId getUuid() {
        return uuid;
    }

    public void setUuid(ObjectId uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<ObjectId> getFarmsAccessible() {
        return farmsAccessible;
    }

    public void setFarmsAccessible(List<ObjectId> farmsAccessible) {
        this.farmsAccessible = farmsAccessible;
    }

    public String getFirebaseUuid() {
        return firebaseUuid;
    }

    public void setFirebaseUuid(String firebaseUuid) {
        this.firebaseUuid = firebaseUuid;
    }

    public List<String> getFirebaseNotificationTokens() {
        return firebaseNotificationTokens;
    }

    public void setFirebaseNotificationTokens(List<String> firebaseNotificationTokens) {
        this.firebaseNotificationTokens = firebaseNotificationTokens;
    }
}
