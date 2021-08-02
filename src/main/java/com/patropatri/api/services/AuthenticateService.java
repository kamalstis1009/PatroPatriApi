package com.patropatri.api.services;

import com.patropatri.api.models.Authenticate;
import com.patropatri.api.repositories.AuthenticateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticateService implements IAuthenticateService {

    @Autowired
    private AuthenticateRepository mRepository;

    @Override
    public Authenticate addObject(Authenticate item) {
        return mRepository.save(item);
    }

    @Override
    public Authenticate getObjectById(UUID id) {
        return null;
    }

    @Override
    public Authenticate updateObject(Authenticate item) {
        return null;
    }

    @Override
    public int deleteObjectById(UUID id) {
        return 0;
    }
}
