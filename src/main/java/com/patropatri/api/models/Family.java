package com.patropatri.api.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "galleries")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "relation")
    private String relation;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "summary")
    private String summary;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;
}
