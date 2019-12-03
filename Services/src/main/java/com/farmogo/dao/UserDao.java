package com.farmogo.dao;

import com.farmogo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    User save(User user);

    void delete(User user);

    User get(String id);

    User getByFirebaseUuid(String firebaseUuid);
}
