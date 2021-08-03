package com.patropatri.api.services;

import com.patropatri.api.models.Authenticate;

import java.util.List;
import java.util.UUID;

public interface AuthenticateService {

    Authenticate addObject(Authenticate item);
    Authenticate getObjectById(UUID id);
    List<Authenticate> getAllObjects();
    Authenticate updateObject(Authenticate item, UUID id);
    int deleteObjectById(UUID id);

}
