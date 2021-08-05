package com.patropatri.api.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "likes")
public class Liked {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@Column(name = "amount")
    //private int amount;

    @Column(name = "user_id")
    private long userId;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private long createdBy;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

}
