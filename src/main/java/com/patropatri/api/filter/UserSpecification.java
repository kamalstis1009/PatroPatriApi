package com.patropatri.api.filter;

import com.patropatri.api.models.User;
import com.patropatri.api.models.UserFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class UserSpecification {

    Logger logger = LoggerFactory.getLogger(UserSpecification.class);

    public Specification<User> getUsersByFilter(UserFilter request) {
        logger.info("YEAR=========== " + request.getFromYear() +" - "+ request.getToYear());
        int year = Calendar.getInstance().get(Calendar.YEAR);
        request.setFromYear(year - request.getFromYear());
        request.setToYear(year - request.getToYear());
        logger.info("YEAR=========== " + request.getFromYear() +" - "+ request.getToYear());

        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> list = new ArrayList<>();
                if (request.getCitizenship() != null && !request.getCitizenship().isEmpty()) {
                    list.add(builder.equal(root.get("citizenship"), request.getCitizenship()));
                }
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
                    list.add(builder.between(builder.function("YEAR", Integer.class, root.get("birthDate")), request.getToYear(), request.getFromYear())); //salary range: 2000, 4000
                }
                list.add(builder.isTrue(root.get("isActive").as(Boolean.class)));
                list.add(builder.isTrue(root.get("isApproved").as(Boolean.class)));
                //list.add(builder.like(builder.lower(root.get("maritalStatus")), "%" + request.getMaritalStatus().toLowerCase() + "%"));
                //query.orderBy(builder.desc(root.get("district")));
                return builder.and(list.toArray(new Predicate[0]));
            }
        };
    }

}
