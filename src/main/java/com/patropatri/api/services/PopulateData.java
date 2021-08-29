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

        //'1', '2021-08-11', 'Jessore', '0', 'Subra Systems Ltd.', 'Karwan Bazar, Dhaka, Bangladesh', 'www.subrasystems.com.bd', 'Fair', '2021-08-10 20:15:01.485000', '1', 'Software Engineer', 'Madaripur', 'mk@gmail.com', 'bibahabd-vue', 'Mustofa Kamal', 'Male', '5.4', NULL, 'IMG_20210810_202307_691.jpg', 'bibahabd-vue', '0', '0', '0', 'Never Married', 'bibahabd-vue', 'Kachukhet, Dhaka, Bangladesh', '01914141707', 'Kachukhet, Dhaka Bangladesh', 'Job Holder', 'Muslim', 'bibahabd-vue', NULL, NULL, NULL, '60 KG'
    }

}
