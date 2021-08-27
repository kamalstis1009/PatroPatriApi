package com.patropatri.api.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "divisions")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country_id")
    private Long countryId;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

}
