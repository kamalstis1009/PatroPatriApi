package com.patropatri.api.services;

import com.patropatri.api.models.User;
import com.patropatri.api.repositories.UserRepository;
import com.patropatri.api.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository mRepository;

    //======================================================| ADD
    @Override
    public User addObject(User item) {
        return mRepository.save(item);
    }

    //======================================================| GET
    @Override
    public User getObjectById(long id) {
        Optional<User> item = mRepository.findById(id);
		if(item.isPresent()) {
			return item.get();
		} else {
			throw new ResourceNotFoundException("Authenticate", "Id", id);
		}
		//return mRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Authenticate", "Id", id));
    }

    @Override
    public List<User> getAllObjects() {
        return mRepository.findAll();
    }

    //======================================================| UPDATE
    @Override
    public User updateObject(User item) {
        Optional<User> response = mRepository.findById(item.getId());
        if (response.isPresent()) {
            User model = response.get();
            //model.setPassword(item.getPassword());
            //model.setUsername(item.getUsername());
            //model.setRole(item.getRole());
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
