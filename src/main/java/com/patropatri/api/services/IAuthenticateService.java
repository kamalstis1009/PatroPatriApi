package com.patropatri.api.services;

import com.patropatri.api.models.Authenticate;

import java.util.UUID;

public interface IAuthenticateService {

    public Authenticate addObject(Authenticate item);
    public Authenticate getObjectById(UUID id);
    public Authenticate updateObject(Authenticate item);
    public int deleteObjectById(UUID id);

}
