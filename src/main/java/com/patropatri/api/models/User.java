package com.patropatri.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long id;

    //@Column(name = "full_name", length=250, nullable=false)
    //@Column(name="Price", columnDefinition="Decimal(10,2) default '100.00'")
    //@Column(columnDefinition = "varchar(255) default 'John Snow'")
    //@Column(columnDefinition = "integer default 25")
    //@Column(columnDefinition = "boolean default false")

    //---------------------| Personal
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @JsonFormat(pattern="dd-MM-yyyy") //yyyy-MM-dd'T'HH:mm:ss
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Transient
    private Integer age;

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

    @Column(name = "citizenship")
    private String citizenship;

    @Column(name = "mother_tongue")
    private String motherTongue;

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
    @Column(name = "summary", length = 1550)
    private String summary;

    @Column(name = "interest", length = 1550)
    private String interest;

    @Column(name = "other_activity", length = 1550)
    private String otherActivity;

    @Column(name = "expectation", length = 1550)
    private String expectation;

    //---------------------| Image
    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_cover_name")
    private String imageCoverName;

    @Column(name = "token")
    private String token;

    @Column(name = "subscription")
    private String subscription;

    @Column(name = "is_active", columnDefinition = "boolean default false")
    private Boolean isActive;

    @Column(name = "is_approved", columnDefinition = "boolean default false")
    private Boolean isApproved;

    //---------------------| Count
    @Column(name = "comment_amount", columnDefinition = "integer default 0")
    private Integer commentAmount;

    @Column(name = "liked_amount", columnDefinition = "integer default 0")
    private Integer likedAmount;

    @Column(name = "viewer_amount", columnDefinition = "integer default 0")
    private Integer viewerAmount;

    //---------------------| Audit
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt; // String.valueOf(new Timestamp(System.currentTimeMillis()));

    @Column(name = "created_by", columnDefinition = "integer default 0")
    private Long createdBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by", columnDefinition = "integer default 0")
    private Long updatedBy;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
