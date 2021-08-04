package com.patropatri.api.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@Column(name = "full_name", length=250, nullable=false)

    //---------------------| Personal
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Transient
    private int age;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "religion")
    private String religion;

    @Column(name = "complex")
    private String complex;

    @Column(name = "district")
    private String district;

    //---------------------| Profession
    @Column(name = "profession")
    private String profession;

    @Column(name = "designation")
    private String designation;

    @Column(name = "company")
    private String company;

    @Column(name = "company_address")
    private String companyAddress;

    @Column(name = "company_website")
    private String companyWebsite;

    //---------------------| Contact
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "present_address")
    private String presentAddress;

    @Column(name = "permanent_address")
    private String permanentAddress;

    //---------------------| Other
    @Column(name = "summary")
    private String summary;

    @Column(name = "interest")
    private String interest;

    @Column(name = "other_activity")
    private String otherActivity;

    @Column(name = "expectation")
    private String expectation;

    //---------------------| Image
    @Column(name = "image_name")
    private String imageName;

    //@Column(name = "image_path")
    //private String imagePath;

    @Column(name = "image_cover_name")
    private String imageCoverName;

    //@Column(name = "cover_image_path")
    //private String coverImagePath;

    @Column(name = "token")
    private String token;

    //---------------------| Count
    @Column(name = "comment_amount")
    private int commentAmount;

    @Column(name = "liked_amount")
    private int likedAmount;

    //---------------------| Audit
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt; // String.valueOf(new Timestamp(System.currentTimeMillis()));

    @Column(name = "created_by")
    private long createdBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private long updatedBy;
}
