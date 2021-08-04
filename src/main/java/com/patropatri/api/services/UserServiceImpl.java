package com.patropatri.api.services;

import com.patropatri.api.models.Candidate;
import com.patropatri.api.models.Education;
import com.patropatri.api.models.Family;
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

    //======================================================| Home
    @Override
    public List<Candidate> getAllCandidates() {
        return null;
    }

    //======================================================| User
    @Override
    public User addUser(User item) {
        return mRepository.save(item);
    }

    @Override
    public User getUserById(long id) {
        Optional<User> item = mRepository.findById(id);
		if(item.isPresent()) {
			return item.get();
		} else {
			throw new ResourceNotFoundException("Authenticate", "Id", id);
		}
		//return mRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Authenticate", "Id", id));
    }

    @Override
    public User updateUser(User item) {
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

    //======================================================| Family
    @Override
    public Family addFamily(Family item) {
        return null;
    }

    @Override
    public Family getFamilyById(long id) {
        return null;
    }

    @Override
    public List<Family> getAllFamilies() {
        return null;
    }

    //======================================================| Education
    @Override
    public Education addEducation(Education item) {
        return null;
    }

    @Override
    public Education getEducationById(long id) {
        return null;
    }

    @Override
    public List<Education> getAllEducations() {
        return null;
    }

}
