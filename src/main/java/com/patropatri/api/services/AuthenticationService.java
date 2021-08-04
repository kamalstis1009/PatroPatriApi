package com.patropatri.api.services;

import com.patropatri.api.models.Authentication;

import java.util.List;

public interface AuthenticationService {

    Authentication addObject(Authentication item);
    Authentication getObjectById(long id);
    List<Authentication> getAllObjects();
    Authentication updateObject(Authentication item);
    int deleteObjectById(long id);

}
