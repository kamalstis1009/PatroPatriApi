package com.patropatri.api.services;

import com.patropatri.api.models.User;

import java.util.List;

public interface UserService {

    User addObject(User item);
    User getObjectById(long id);
    List<User> getAllObjects();
    User updateObject(User item);
    int deleteObjectById(long id);

}
