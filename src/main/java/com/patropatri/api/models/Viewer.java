package com.patropatri.api.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "viewers")
public class Viewer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Column(name = "amount")
    //private int amount;

    @Column(name = "user_id")
    private Long userId;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

}
