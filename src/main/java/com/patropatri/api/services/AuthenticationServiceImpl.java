package com.patropatri.api.services;

import com.patropatri.api.models.Authentication;
import com.patropatri.api.repositories.AuthenticationRepository;
import com.patropatri.api.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationRepository mRepository;

    //======================================================| ADD
    @Override
    public Authentication addObject(Authentication item) {
        Authentication model = mRepository.getAuthenticateByUsername(item.getUsername());
        if (model == null)
            return mRepository.save(item);
        else
            return null;
    }

    //======================================================| GET
    @Override
    public Authentication getObjectById(long id) {
        Optional<Authentication> item = mRepository.findById(id);
		if(item.isPresent()) {
			return item.get();
		} else {
			throw new ResourceNotFoundException("Authenticate", "Id", id);
		}
		//return mRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Authenticate", "Id", id));
    }

    @Override
    public List<Authentication> getAllObjects() {
        return mRepository.findAll();
    }

    //======================================================| UPDATE
    @Override
    public Authentication updateObject(Authentication item) {
        Optional<Authentication> response = mRepository.findById(item.getId());
        if (response.isPresent()) {
            Authentication model = response.get();
            model.setPassword(item.getPassword());
            model.setUsername(item.getUsername());
            model.setRole(item.getRole());
            mRepository.save(model);
            return model;
        } else {
            return null;
        }
    }

    //======================================================| DELETE
    @Override
    public int deleteObjectById(long id) {
        boolean isFound = mRepository.existsById(id);
        if(isFound) {
            mRepository.deleteById(id);
            return 1;
        }
        return 0;
    }
}
