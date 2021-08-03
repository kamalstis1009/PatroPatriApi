package com.patropatri.api.services;

import com.patropatri.api.models.Authenticate;
import com.patropatri.api.repositories.AuthenticateRepository;
import com.patropatri.api.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    private AuthenticateRepository mRepository;

    @Override
    public Authenticate addObject(Authenticate item) {
        return mRepository.save(item);
    }

    @Override
    public Authenticate getObjectById(UUID id) {
        Optional<Authenticate> item = mRepository.findById(id);
		if(item.isPresent()) {
			return item.get();
		} else {
			throw new ResourceNotFoundException("Authenticate", "Id", id);
		}
		//return mRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Authenticate", "Id", id));
    }

    @Override
    public List<Authenticate> getAllObjects() {
        return mRepository.findAll();
    }

    @Override
    public Authenticate updateObject(Authenticate item) {
        Optional<Authenticate> response = mRepository.findById(item.getId());
        if (response.isPresent()) {
            Authenticate model = response.get();
            model.setPassword(item.getPassword());
            model.setUsername(item.getUsername());
            model.setRole(item.getRole());
            mRepository.save(model);
            return model;
        } else {
            return null;
        }
    }

    @Override
    public int deleteObjectById(UUID id) {
        boolean isFound = mRepository.existsById(id);
        if(isFound) {
            mRepository.deleteById(id);
            return 1;
        }
        return 0;
    }
}
