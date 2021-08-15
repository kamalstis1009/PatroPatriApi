package com.patropatri.api.services;

import com.patropatri.api.filter.UserSpecification;
import com.patropatri.api.models.*;
import com.patropatri.api.repositories.EducationRepository;
import com.patropatri.api.repositories.FamilyRepository;
import com.patropatri.api.repositories.UserRepository;
import com.patropatri.api.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private UserSpecification mSpecification;

    //======================================================| Home
    @Override
    public List<ICandidate> getAllCandidates() {
        return mRepository.getAllCandidates();
    }

    @Override
    public List<ICandidate> getAllCandidatesByPaginated(int pageNo, int rowSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, rowSize);
        return mRepository.getAllCandidatesByPaginated(pageable);
    }

    @Override
    public List<User> getUsersByFilter(UserFilter request, int pageNo, int pageSize) {
        if (pageNo == 0) {
            Page<User> pages = new PageImpl<>(mRepository.findAll(mSpecification.getUsersByFilter(request)));
            return pages.getContent();
        } else {
            Pageable paging = PageRequest.of(pageNo - 1, pageSize);
            Page<User> pages = mRepository.findAll(mSpecification.getUsersByFilter(request), paging);
            return pages.getContent();
        }
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
        if (item.getId() != null) {
            Optional<User> response = mRepository.findById(item.getId());
            if (response.isPresent()) {
                User model = extracted(item, response.get());
                mRepository.save(model);
                return model;
            } else {
                User model = extracted(item, new User());
                return addUser(model);
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean updateUserPost(long id, boolean isActive) {
        Optional<User> response = mRepository.findById(id);
        if (response.isPresent()) {
            User item = response.get();
            item.setIsActive(isActive);
            mRepository.save(item);
            return true;
        }
        return false;
    }

    private User extracted(User item, User model) {
        if (item.getFullName() != null)
            model.setFullName(item.getFullName());
        if (item.getGender() != null)
            model.setGender(item.getGender());
        if (item.getBirthDate() != null)
            model.setBirthDate(item.getBirthDate());
        if (item.getBirthPlace() != null)
            model.setBirthPlace(item.getBirthPlace());
        if (item.getHeight() != null)
            model.setHeight(item.getHeight());
        if (item.getWeight() != null)
            model.setWeight(item.getWeight());
        if (item.getMaritalStatus() != null)
            model.setMaritalStatus(item.getMaritalStatus());
        if (item.getReligion() != null)
            model.setReligion(item.getReligion());
        if (item.getComplex() != null)
            model.setComplex(item.getComplex());
        if (item.getDistrict() != null)
            model.setDistrict(item.getDistrict());
        if (item.getProfession() != null)
            model.setProfession(item.getProfession());
        if (item.getDesignation() != null)
            model.setDesignation(item.getDesignation());
        if (item.getCompany() != null)
            model.setCompany(item.getCompany());
        if (item.getCompanyAddress() != null)
            model.setCompanyAddress(item.getCompanyAddress());
        if (item.getCompanyWebsite() != null)
            model.setCompanyWebsite(item.getCompanyWebsite());
        if (item.getPhoneNumber() != null)
            model.setPhoneNumber(item.getPhoneNumber());
        if (item.getEmail() != null)
            model.setEmail(item.getEmail());
        if (item.getPresentAddress() != null)
            model.setPresentAddress(item.getPresentAddress());
        if (item.getPermanentAddress() != null)
            model.setPermanentAddress(item.getPermanentAddress());
        if (item.getSummary() != null)
            model.setSummary(item.getSummary());
        if (item.getInterest() != null)
            model.setInterest(item.getInterest());
        if (item.getOtherActivity() != null)
            model.setOtherActivity(item.getOtherActivity());
        if (item.getExpectation() != null)
            model.setExpectation(item.getExpectation());
        if (item.getImageName() != null)
            model.setImageName(item.getImageName());
        if (item.getImageCoverName() != null)
            model.setImageCoverName(item.getImageCoverName());
        if (item.getToken() != null)
            model.setToken(item.getToken());

        model.setIsActive(item.getIsActive() != null);
        model.setCommentAmount(item.getCommentAmount() != null ? item.getCommentAmount() : 0);
        model.setLikedAmount(item.getLikedAmount() != null ? item.getLikedAmount() : 0);
        model.setId(item.getId());
        model.setCreatedBy(item.getId());
        //model.setCreatedAt(item.getCreatedAt());
        //model.setUpdatedAt(item.getUpdatedAt());
        //model.setUpdatedBy(item.getUpdatedBy());
        return model;
    }

    //======================================================| Family
    @Override
    public Family addFamily(Family item) {
        return mFamilyRep.save(item);
    }

    @Override
    public int deleteFamilyById(long id) {
        boolean isFound = mFamilyRep.existsById(id);
        if(isFound) {
            mFamilyRep.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Family> getAllFamiliesByUserId(long userId) {
        return mFamilyRep.getAllFamiliesByUserId(userId);
        //return mFamilyRep.findAll();
    }

    //======================================================| Education
    @Override
    public Education addEducation(Education item) {
        return mEducationRep.save(item);
    }

    @Override
    public int deleteEducationById(long id) {
        boolean isFound = mEducationRep.existsById(id);
        if(isFound) {
            mEducationRep.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Education> getAllEducationsByUserId(long userId) {
        return mEducationRep.getAllEducationsByUserId(userId);
        //return mEducationRep.findAll();
    }

}
