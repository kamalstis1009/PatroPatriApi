package com.patropatri.api.services;

import com.patropatri.api.models.Candidate;
import com.patropatri.api.models.Education;
import com.patropatri.api.models.Family;
import com.patropatri.api.models.User;
import com.patropatri.api.repositories.EducationRepository;
import com.patropatri.api.repositories.FamilyRepository;
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

    @Autowired
    private FamilyRepository mFamilyRep;

    @Autowired
    private EducationRepository mEducationRep;

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
            model.setFullName(item.getFullName());
            model.setGender(item.getGender());
            model.setBirthDate(item.getBirthDate());
            model.setBirthPlace(item.getBirthPlace());
            model.setHeight(item.getHeight());
            model.setWeight(item.getWeight());
            model.setMaritalStatus(item.getMaritalStatus());
            model.setReligion(item.getReligion());
            model.setComplex(item.getComplex());
            model.setDistrict(item.getDistrict());
            model.setProfession(item.getProfession());
            model.setDesignation(item.getDesignation());
            model.setCompany(item.getCompany());
            model.setCompanyAddress(item.getCompanyAddress());
            model.setCompanyWebsite(item.getCompanyWebsite());
            model.setPhoneNumber(item.getPhoneNumber());
            model.setEmail(item.getEmail());
            model.setPresentAddress(item.getPresentAddress());
            model.setPermanentAddress(item.getPermanentAddress());
            model.setSummary(item.getSummary());
            model.setInterest(item.getInterest());
            model.setOtherActivity(item.getOtherActivity());
            model.setExpectation(item.getExpectation());
            model.setImageName(item.getImageName());
            model.setImageCoverName(item.getImageCoverName());
            model.setToken(item.getToken());
            //model.setCreatedAt(item.getCreatedAt());
            //model.setCreatedBy(item.getCreatedBy());
            //model.setUpdatedAt(item.getUpdatedAt());
            //model.setUpdatedBy(item.getUpdatedBy());
            mRepository.save(model);
            return model;
        } else {
            return null;
        }
    }

    //======================================================| Family
    @Override
    public Family addFamily(Family item) {
        return mFamilyRep.save(item);
    }

    @Override
    public Family getFamilyById(long id) {
        Optional<Family> item = mFamilyRep.findById(id);
        if(item.isPresent()) {
            return item.get();
        } else {
            throw new ResourceNotFoundException("Family", "Id", id);
        }
    }

    @Override
    public List<Family> getAllFamilies() {
        return mFamilyRep.findAll();
    }

    //======================================================| Education
    @Override
    public Education addEducation(Education item) {
        return mEducationRep.save(item);
    }

    @Override
    public Education getEducationById(long id) {
        Optional<Education> item = mEducationRep.findById(id);
        if(item.isPresent()) {
            return item.get();
        } else {
            throw new ResourceNotFoundException("Education", "Id", id);
        }
    }

    @Override
    public List<Education> getAllEducations() {
        return mEducationRep.findAll();
    }

}
