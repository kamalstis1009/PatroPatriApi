package com.patropatri.api.services;

import com.patropatri.api.models.Authentication;
import com.patropatri.api.models.Subscription;
import com.patropatri.api.models.User;
import com.patropatri.api.repositories.AuthenticationRepository;
import com.patropatri.api.repositories.SubscriptionRepository;
import com.patropatri.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PopulateData implements CommandLineRunner {

    @Autowired
    private AuthenticationRepository mAuthenticationRep;

    @Autowired
    private UserRepository mUserRepositoryRep;

    @Autowired
    private SubscriptionRepository mSubscriptionRep;

    @Override
    public void run(String... args) throws Exception {

        //======================================================| Authentication
        Authentication auth1 = new Authentication();
        auth1.setId(1L);
        auth1.setUsername("01914141707");
        auth1.setPassword("123");
        auth1.setRole("USER");
        mAuthenticationRep.save(auth1);

        Authentication auth2 = new Authentication();
        auth2.setId(2L);
        auth2.setUsername("01914141708");
        auth2.setPassword("123");
        auth2.setRole("USER");
        mAuthenticationRep.save(auth2);

        //======================================================| User
        User user = new User();
        user.setId(1L);
        user.setFullName("Mustofa Kamal");
        user.setGender("Male");
        user.setBirthPlace("1990-08-11");
        user.setBirthPlace("Jessore");
        user.setHeight("5'.4\"");
        user.setWeight("60 KG");
        user.setMaritalStatus("Never Married");
        user.setReligion("Islam");
        user.setComplex("Fair");
        user.setDistrict("Madaripur");
        user.setCitizenship("Bangladesh");
        user.setMotherTongue("Bangla");
        user.setProfession("Job Holder");
        user.setDesignation("Software Engineer");
        user.setCompany("Subra Systems Ltd.");
        user.setCompanyAddress("Karwan Bazar, Dhaka, Bangladesh");
        user.setCompanyWebsite("www.subrasystems.com.bd");
        user.setPhoneNumber("+8801914141707");
        user.setEmail("mk@gmail.com");
        user.setAddressLine1("Kachukhe, Dhaka Cantonment");
        user.setCountry1("Bangladesh");
        user.setDivision1("Dhaka");
        user.setDistrict1("Dhaka");
        user.setPoliceStation1("Kafrul");
        user.setPostalCode1("1206");
        mUserRepositoryRep.save(user);

        //======================================================| Subscription
        Subscription sub1 = new Subscription();
        sub1.setName("Monthly");
        sub1.setPrice(500.00);
        mSubscriptionRep.save(sub1);
        Subscription sub2 = new Subscription();
        sub2.setName("Quarterly");
        sub2.setPrice(1000.00);
        mSubscriptionRep.save(sub2);
        Subscription sub3 = new Subscription();
        sub3.setName("Half-Yearly");
        sub3.setPrice(1500.00);
        mSubscriptionRep.save(sub3);
        Subscription sub4 = new Subscription();
        sub4.setName("Annually");
        sub4.setPrice(2000.00);
        mSubscriptionRep.save(sub4);

    }

}
