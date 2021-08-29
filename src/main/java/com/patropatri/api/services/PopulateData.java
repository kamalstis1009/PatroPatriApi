package com.patropatri.api.services;

import com.patropatri.api.models.Subscription;
import com.patropatri.api.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PopulateData implements CommandLineRunner {

    @Autowired
    private SubscriptionRepository mSubscriptionRep;

    @Override
    public void run(String... args) throws Exception {

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
