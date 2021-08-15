package com.patropatri.api.filter;

import com.patropatri.api.models.User;
import com.patropatri.api.models.UserFilter;

import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification {

    public Specification<User> getUsers(UserFilter request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getGender() != null && !request.getGender().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), request.getGender()));
            }
            if (request.getMaritalStatus() != null && !request.getMaritalStatus().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("maritalStatus")), "%" + request.getMaritalStatus().toLowerCase() + "%"));
            }
            if (request.getReligion() != null && !request.getReligion().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("religion"), request.getReligion()));
            }
            if (request.getProfession() != null && !request.getProfession().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("profession"), request.getProfession()));
            }
            if (request.getFromYear() != null && request.getFromYear() > 0 && request.getToYear() != null && request.getToYear() > 0) {
                predicates.add(criteriaBuilder.between(root.get("birthDate"), request.getFromYear(), request.getToYear()));
            }
            if (request.getDistrict() != null && !request.getDistrict().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("district"), request.getDistrict()));
            }
            //query.orderBy(criteriaBuilder.desc(root.get("createdAt")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
