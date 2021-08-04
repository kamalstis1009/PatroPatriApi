package com.patropatri.api.services;

import com.patropatri.api.models.Candidate;
import com.patropatri.api.models.Education;
import com.patropatri.api.models.Family;
import com.patropatri.api.models.User;

import java.util.List;

public interface UserService {

    List<Candidate> getAllCandidates();

    //------------------------------------------------------| User
    User addUser(User item);
    User getUserById(long id);
    User updateUser(User item);

    //------------------------------------------------------| Family
    Family addFamily(Family item);
    Family getFamilyById(long id);
    List<Family> getAllFamilies();

    //------------------------------------------------------| Education
    Education addEducation(Education item);
    Education getEducationById(long id);
    List<Education> getAllEducations();

}
