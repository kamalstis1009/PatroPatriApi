package com.patropatri.api.services;

import com.patropatri.api.models.*;
import com.patropatri.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PopulateData implements CommandLineRunner {

    @Autowired
    private PasswordEncoder mEncoder;

    @Autowired
    private AuthenticationRepository mAuthenticationRep;

    @Autowired
    private UserRepository mUserRepositoryRep;

    @Autowired
    private SubscriptionRepository mSubscriptionRep;

    @Autowired
    private CountryRepository mCountryRepository;

    @Autowired
    private DivisionRepository mDivisionRepository;

    @Autowired
    private DistrictRepository mDistrictRepository;

    @Autowired
    private PoliceStationRepository mPoliceStationRepository;

    @Autowired
    private PostalCodeRepository mPostalCodeRepository;

    @Override
    public void run(String... args) throws Exception {

        //======================================================| Subscription
        storeSubscriptions();

        //======================================================| Authentication
        storeAuths();

        //======================================================| User
        storeUsers();

        //======================================================| Country
        storeCountries();
        storeDivisions();
        storeDistricts();
        storePoliceStations();
        storePostalCodes();

    }

    private void storeSubscriptions() {
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
    }

    private void storeAuths() {
        Authentication auth1 = new Authentication();
        auth1.setId(1L);
        auth1.setUsername("01914141707");
        auth1.setPassword(mEncoder.encode("123"));
        auth1.setRole("USER");
        mAuthenticationRep.save(auth1);

        Authentication auth2 = new Authentication();
        auth2.setId(2L);
        auth2.setUsername("01914141708");
        auth2.setPassword(mEncoder.encode("123"));
        auth2.setRole("USER");
        mAuthenticationRep.save(auth2);
    }

    private void storeUsers() {
        User user = new User();
        user.setId(1L);
        user.setFullName("Monirul Islam");
        user.setGender("Male");
        user.setBirthDate(new Date());
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
        user.setSubscriptionId(1L);
        user.setSubscriptionExpired(new Date());
        user.setIsActive(true);
        user.setIsApproved(true);
        user.setCommentAmount(1);
        user.setLikedAmount(1);
        user.setViewerAmount(1);
        mUserRepositoryRep.save(user);

        User user2 = new User();
        user2.setId(2L);
        user2.setFullName("Zaman Uddin Khan");
        user2.setGender("Male");
        user2.setBirthDate(new Date());
        user2.setBirthPlace("Sylhet");
        user2.setHeight("5'.4\"");
        user2.setWeight("60 KG");
        user2.setMaritalStatus("Never Married");
        user2.setReligion("Islam");
        user2.setComplex("Fair");
        user2.setDistrict("Sylhet");
        user2.setCitizenship("Bangladesh");
        user2.setMotherTongue("Bangla");
        user2.setProfession("Job Holder");
        user2.setDesignation("Engineer");
        user2.setCompany("Subra Systems Ltd.");
        user2.setCompanyAddress("Karwan Bazar, Dhaka, Bangladesh");
        user2.setCompanyWebsite("www.subrasystems.com.bd");
        user2.setPhoneNumber("+8801914141707");
        user2.setEmail("mk@gmail.com");
        user2.setAddressLine1("Kachukhe, Dhaka Cantonment");
        user2.setCountry1("Bangladesh");
        user2.setDivision1("Dhaka");
        user2.setDistrict1("Dhaka");
        user2.setPoliceStation1("Kafrul");
        user2.setPostalCode1("1206");
        user2.setAddressLine2("Kachukhe, Dhaka Cantonment");
        user2.setCountry2("Bangladesh");
        user2.setDivision2("Dhaka");
        user2.setDistrict2("Dhaka");
        user2.setPoliceStation2("Kafrul");
        user2.setPostalCode2("1206");
        user2.setSummary("I am a person who is positive about every aspect of life. There are many things I like to do, to see, and to experience. I like to read, I like to write; I like to think, I like to dream; I like to talk, I like to listen. I like to see the sunrise in the morning, I like to see the moonlight at night; I like to feel the music flowing on my face, I like to smell the wind coming from the ocean. I like to look at the clouds in the sky with a blank mind, I like to do thought experiment when I cannot sleep in the middle of the night. I like flowers in spring, rain in summer, leaves in autumn, and snow in winter. I like to sleep early, I like to get up late; I like to be alone, I like to be surrounded by people. I like country’s peace, I like metropolis’ noise; I like the beautiful west lake in Hangzhou, I like the flat cornfield in Champaign. I like delicious food and comfortable shoes; I like good books and romantic movies. I like the land and the nature, I like people. And, I like to laugh.");
        user2.setInterest("I am a person who is positive about every aspect of life. There are many things I like to do, to see, and to experience. I like to read, I like to write; I like to think, I like to dream; I like to talk, I like to listen. I like to see the sunrise in the morning, I like to see the moonlight at night; I like to feel the music flowing on my face, I like to smell the wind coming from the ocean. I like to look at the clouds in the sky with a blank mind, I like to do thought experiment when I cannot sleep in the middle of the night. I like flowers in spring, rain in summer, leaves in autumn, and snow in winter. I like to sleep early, I like to get up late; I like to be alone, I like to be surrounded by people. I like country’s peace, I like metropolis’ noise; I like the beautiful west lake in Hangzhou, I like the flat cornfield in Champaign. I like delicious food and comfortable shoes; I like good books and romantic movies. I like the land and the nature, I like people. And, I like to laugh.");
        user2.setOtherActivity("I am a person who is positive about every aspect of life. There are many things I like to do, to see, and to experience. I like to read, I like to write; I like to think, I like to dream; I like to talk, I like to listen. I like to see the sunrise in the morning, I like to see the moonlight at night; I like to feel the music flowing on my face, I like to smell the wind coming from the ocean. I like to look at the clouds in the sky with a blank mind, I like to do thought experiment when I cannot sleep in the middle of the night. I like flowers in spring, rain in summer, leaves in autumn, and snow in winter. I like to sleep early, I like to get up late; I like to be alone, I like to be surrounded by people. I like country’s peace, I like metropolis’ noise; I like the beautiful west lake in Hangzhou, I like the flat cornfield in Champaign. I like delicious food and comfortable shoes; I like good books and romantic movies. I like the land and the nature, I like people. And, I like to laugh.");
        user2.setExpectation("I am a person who is positive about every aspect of life. There are many things I like to do, to see, and to experience. I like to read, I like to write; I like to think, I like to dream; I like to talk, I like to listen. I like to see the sunrise in the morning, I like to see the moonlight at night; I like to feel the music flowing on my face, I like to smell the wind coming from the ocean. I like to look at the clouds in the sky with a blank mind, I like to do thought experiment when I cannot sleep in the middle of the night. I like flowers in spring, rain in summer, leaves in autumn, and snow in winter. I like to sleep early, I like to get up late; I like to be alone, I like to be surrounded by people. I like country’s peace, I like metropolis’ noise; I like the beautiful west lake in Hangzhou, I like the flat cornfield in Champaign. I like delicious food and comfortable shoes; I like good books and romantic movies. I like the land and the nature, I like people. And, I like to laugh.");
        user2.setReference("Nothing...");
        user2.setSubscriptionId(1L);
        user2.setSubscriptionExpired(new Date());
        user2.setIsActive(true);
        user2.setIsApproved(true);
        user2.setCommentAmount(1);
        user2.setLikedAmount(1);
        user2.setViewerAmount(1);
        mUserRepositoryRep.save(user2);
    }

    private void storeCountries() {
        String [] arr = { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei ", "Bulgaria", "Burkina Faso", "Burundi", "Côte d\'Ivoire", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo (Congo-Brazzaville)", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czechia (Czech Republic)", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini (fmr. \"Swaziland\")", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Holy See", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (formerly Burma)", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan", "Palau", "Palestine State", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States of America", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe" };
        for (int i=0; i<arr.length; i++) {
            Country model = new Country();
            model.setId(i+1L);
            model.setName(arr[i]);
            model.setCode(model.getId() + "");
            mCountryRepository.save(model);
        }
    }

    private void storeDivisions() {
        String [] arr = { "Barisal", "Chittagong", "Dhaka", "Khulna", "Mymensingh", "Rangpur", "Rajshahi", "Sylhet" };
        for (int i=0; i<arr.length; i++) {
            Division model = new Division();
            model.setId(i+1L);
            model.setName(arr[i]);
            model.setCountryId(14L);
            mDivisionRepository.save(model);
        }
    }

    private void storeDistricts() {
        String [] arr = { "Bagerhat", "Bandarban", "Barguna", "Barishal", "Bhola", "Bogura", "Brahmanbaria", "Chandpur", "Chapainawabganj", "Chattogram", "Chuadanga", "Cox\'s Bazar", "Cumilla", "Dhaka", "Dinajpur", "Faridpur", "Feni", "Gaibandha", "Gazipur", "Gopalganj", "Habiganj", "Jamalpur", "Jashore", "Jhalokati", "Jhenaidah", "Joypurhat", "Khagrachhari", "Khulna", "Kishoreganj", "Kurigram", "Kushtia", "Lakshmipur", "Lalmonirhat", "Madaripur", "Magura", "Manikganj", "Meherpur", "Moulvibazar", "Munshiganj", "Mymensingh", "Naogaon", "Narail", "Narayanganj", "Narsingdi", "Natore", "Netrokona", "Nilphamari", "Noakhali", "Pabna", "Panchagarh", "Patuakhali", "Pirojpur", "Rajbari", "Rajshahi", "Rangamati", "Rangpur", "Satkhira", "Shariatpur", "Sherpur", "Sirajganj", "Sunamganj", "Sylhet", "Tangail", "Thakurgaon" };
        for (int i=0; i<arr.length; i++) {
            District model = new District();
            model.setId(i+1L);
            model.setName(arr[i]);
            model.setDivisionId(198L);
            mDistrictRepository.save(model);
        }
    }

    private void storePoliceStations() {
        String [] arr = { "Adabor", "Airport", "Badda", "Banani", "Bangshal", "Bhashantek", "Cantonment", "Chackbazar", "Dakshin Khan", "Darus-Salam", "Demra", "Dhanmondi", "Gandaria", "Gulshan", "Hatirjheel", "Hazaribag", "Jattrabari", "Kadamtoli", "Kafrul", "Kalabagan", "Kamrangirchar", "Khilgaon", "Khilkhet", "Kotwali", "Lalbagh", "Mirpur Model", "Mohammadpur", "Motijheel", "Mugda", "New Market", "Pallabi", "Paltan Model", "Ramna Model", "Rampura", "Rupnagar", "Sabujbag", "Shah Ali", "Shahbag", "Shahjahanpur", "Sher e Bangla Nagar", "Shyampur", "Sutrapur", "Tejgaon", "Tejgaon Industrial", "Turag", "Uttar Khan", "Uttara East", "Uttara West", "Vatara", "Wari" };
        for (int i=0; i<arr.length; i++) {
            PoliceStation model = new PoliceStation();
            model.setId(i+1L);
            model.setName(arr[i]);
            model.setDistrictId(217L);
            mPoliceStationRepository.save(model);
        }
    }

    private void storePostalCodes() {
        String [] arr = { "1000", "1100", "1203", "1204", "1205", "1206", "1207", "1208", "1209", "1211", "1212", "1213", "1214", "1215", "1216", "1217", "1219", "1222", "1223", "1225", "1229", "1230", "1232", "1310", "1311", "1312", "1313", "1320", "1321", "1322", "1323", "1324", "1325", "1330", "1331", "1332", "1340", "1341", "1342", "1343", "1344", "1345", "1346", "1347", "1348", "1349", "1350", "1351", "1360", "1361", "1362", "1000", "1100", "1203", "1204", "1205", "1206", "1207", "1208", "1209", "1211", "1212", "1213", "1214", "1215", "1216", "1217", "1219", "1222", "1223", "1225", "1229", "1230", "1232", "1310", "1311", "1312", "1313", "1320", "1321", "1322", "1323", "1324", "1325", "1330", "1331", "1332", "1340", "1341", "1342", "1343", "1344", "1345", "1346", "1347", "1348", "1349", "1350", "1351", "1360", "1361", "1362" };
        for (int i=0; i<arr.length; i++) {
            PostalCode model = new PostalCode();
            model.setId(i+1L);
            model.setName(arr[i]);
            model.setDistrictId(217L);
            mPostalCodeRepository.save(model);
        }
    }

}
