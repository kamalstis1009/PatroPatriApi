package com.patropatri.api.services;

import com.patropatri.api.models.ICandidate;
import com.patropatri.api.models.Education;
import com.patropatri.api.models.Family;
import com.patropatri.api.models.User;

import java.util.List;

public interface UserService {

    List<ICandidate> getAllCandidates();
    List<ICandidate> getAllCandidatesByPaginated(int pageNo, int rowSize);

    //------------------------------------------------------| User
    User addUser(User item);
    User getUserById(long id);
    User updateUser(User item);
    boolean updateUserPost(long id, boolean isActive);

    //------------------------------------------------------| Family
    Family addFamily(Family item);
    int deleteFamilyById(long id);
    List<Family> getAllFamiliesByUserId(long userId);

    //------------------------------------------------------| Education
    Education addEducation(Education item);
    int deleteEducationById(long id);
    List<Education> getAllEducationsByUserId(long userId);

}
