package com.patropatri.api.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "educations")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "degree")
    private String degree;

    @Column(name = "subject")
    private String subject;

    @Column(name = "passing_year")
    private int passingYear;

    @Column(name = "result")
    private String result;

    @Column(name = "institution")
    private String institution;

    @Column(name = "user_id")
    private long userId;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;
}
