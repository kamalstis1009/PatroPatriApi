package com.patropatri.api.filter;

import com.patropatri.api.models.User;
import com.patropatri.api.models.UserFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification {

    public Specification<User> getUsersByFilter(UserFilter request) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> list = new ArrayList<>();
                if (request.getDistrict() != null && !request.getDistrict().isEmpty()) {
                    list.add(builder.equal(root.get("district"), request.getDistrict()));
                }
                if (request.getGender() != null && !request.getGender().isEmpty()) {
                    list.add(builder.equal(root.get("gender"), request.getGender()));
                }
                if (request.getMaritalStatus() != null && !request.getMaritalStatus().isEmpty()) {
                    list.add(builder.equal(root.get("maritalStatus"), request.getMaritalStatus()));
                }
                if (request.getProfession() != null && !request.getProfession().isEmpty()) {
                    list.add(builder.equal(root.get("profession"), request.getProfession()));
                }
                if (request.getReligion() != null && !request.getReligion().isEmpty()) {
                    list.add(builder.equal(root.get("religion"), request.getReligion()));
                }
                if (request.getFromYear() != null && request.getFromYear() > 0 && request.getToYear() != null && request.getToYear() > 0) {
                    list.add(builder.between(builder.function("YEAR", Integer.class, root.get("birthDate")), request.getFromYear(), request.getToYear()));
                }
                //list.add(builder.like(builder.lower(root.get("maritalStatus")), "%" + request.getMaritalStatus().toLowerCase() + "%"));
                //query.orderBy(builder.desc(root.get("district")));
                return builder.and(list.toArray(new Predicate[0]));
            }
        };
    }

}
