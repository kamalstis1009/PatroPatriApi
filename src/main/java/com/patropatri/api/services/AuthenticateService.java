package com.patropatri.api.services;

import com.patropatri.api.models.Authenticate;

import java.util.List;

public interface AuthenticateService {

    Authenticate addObject(Authenticate item);
    Authenticate getObjectById(long id);
    List<Authenticate> getAllObjects();
    Authenticate updateObject(Authenticate item);
    int deleteObjectById(long id);

}
