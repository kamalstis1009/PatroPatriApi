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

import java.util.Date;

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

        //======================================================| Subscription
        Subscription sub1 = new Subscription();
        sub1.setId(1L);
        sub1.setName("Monthly");
        sub1.setPrice(500.00);
        mSubscriptionRep.save(sub1);
        Subscription sub2 = new Subscription();
        sub2.setId(2L);
        sub2.setName("Quarterly");
        sub2.setPrice(1000.00);
        mSubscriptionRep.save(sub2);
        Subscription sub3 = new Subscription();
        sub3.setId(3L);
        sub3.setName("Half-Yearly");
        sub3.setPrice(1500.00);
        mSubscriptionRep.save(sub3);
        Subscription sub4 = new Subscription();
        sub4.setId(4L);
        sub4.setName("Annually");
        sub4.setPrice(2000.00);
        mSubscriptionRep.save(sub4);

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
        user.setAddressLine2("Kachukhe, Dhaka Cantonment");
        user.setCountry2("Bangladesh");
        user.setDivision2("Dhaka");
        user.setDistrict2("Dhaka");
        user.setPoliceStation2("Kafrul");
        user.setPostalCode2("1206");
        user.setSummary("I am a person who is positive about every aspect of life. There are many things I like to do, to see, and to experience. I like to read, I like to write; I like to think, I like to dream; I like to talk, I like to listen. I like to see the sunrise in the morning, I like to see the moonlight at night; I like to feel the music flowing on my face, I like to smell the wind coming from the ocean. I like to look at the clouds in the sky with a blank mind, I like to do thought experiment when I cannot sleep in the middle of the night. I like flowers in spring, rain in summer, leaves in autumn, and snow in winter. I like to sleep early, I like to get up late; I like to be alone, I like to be surrounded by people. I like country’s peace, I like metropolis’ noise; I like the beautiful west lake in Hangzhou, I like the flat cornfield in Champaign. I like delicious food and comfortable shoes; I like good books and romantic movies. I like the land and the nature, I like people. And, I like to laugh.");
        user.setInterest("I am a person who is positive about every aspect of life. There are many things I like to do, to see, and to experience. I like to read, I like to write; I like to think, I like to dream; I like to talk, I like to listen. I like to see the sunrise in the morning, I like to see the moonlight at night; I like to feel the music flowing on my face, I like to smell the wind coming from the ocean. I like to look at the clouds in the sky with a blank mind, I like to do thought experiment when I cannot sleep in the middle of the night. I like flowers in spring, rain in summer, leaves in autumn, and snow in winter. I like to sleep early, I like to get up late; I like to be alone, I like to be surrounded by people. I like country’s peace, I like metropolis’ noise; I like the beautiful west lake in Hangzhou, I like the flat cornfield in Champaign. I like delicious food and comfortable shoes; I like good books and romantic movies. I like the land and the nature, I like people. And, I like to laugh.");
        user.setOtherActivity("I am a person who is positive about every aspect of life. There are many things I like to do, to see, and to experience. I like to read, I like to write; I like to think, I like to dream; I like to talk, I like to listen. I like to see the sunrise in the morning, I like to see the moonlight at night; I like to feel the music flowing on my face, I like to smell the wind coming from the ocean. I like to look at the clouds in the sky with a blank mind, I like to do thought experiment when I cannot sleep in the middle of the night. I like flowers in spring, rain in summer, leaves in autumn, and snow in winter. I like to sleep early, I like to get up late; I like to be alone, I like to be surrounded by people. I like country’s peace, I like metropolis’ noise; I like the beautiful west lake in Hangzhou, I like the flat cornfield in Champaign. I like delicious food and comfortable shoes; I like good books and romantic movies. I like the land and the nature, I like people. And, I like to laugh.");
        user.setExpectation("I am a person who is positive about every aspect of life. There are many things I like to do, to see, and to experience. I like to read, I like to write; I like to think, I like to dream; I like to talk, I like to listen. I like to see the sunrise in the morning, I like to see the moonlight at night; I like to feel the music flowing on my face, I like to smell the wind coming from the ocean. I like to look at the clouds in the sky with a blank mind, I like to do thought experiment when I cannot sleep in the middle of the night. I like flowers in spring, rain in summer, leaves in autumn, and snow in winter. I like to sleep early, I like to get up late; I like to be alone, I like to be surrounded by people. I like country’s peace, I like metropolis’ noise; I like the beautiful west lake in Hangzhou, I like the flat cornfield in Champaign. I like delicious food and comfortable shoes; I like good books and romantic movies. I like the land and the nature, I like people. And, I like to laugh.");
        user.setReference("Nothing...");
        user.setSubscription(1L);
        user.setSubscriptionExpired(new Date());
        user.setIsActive(true);
        user.setIsApproved(true);
        user.setCommentAmount(1);
        user.setLikedAmount(1);
        user.setViewerAmount(1);
        mUserRepositoryRep.save(user);

    }

}
