package com.patropatri.api.services;

import com.patropatri.api.models.*;

import java.util.List;

public interface UserService {

    List<ICandidate> getAllCandidates();
    List<ICandidate> getAllCandidatesByPaginated(int pageNo, int rowSize);
    List<ICandidate> getUsersByFilter(UserFilter request, int pageNo, int pageSize);

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

    //------------------------------------------------------| Subscription
    List<Subscription> getAllSubscriptions();

}
