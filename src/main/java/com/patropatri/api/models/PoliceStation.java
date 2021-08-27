package com.patropatri.api.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "police_stations")
public class PoliceStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "district_id")
    private Long districtId;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

}
