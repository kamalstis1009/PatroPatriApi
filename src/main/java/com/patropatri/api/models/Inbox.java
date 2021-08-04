package com.patropatri.api.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "inboxes")
public class Inbox {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "message")
    private String message;

    @Column(name = "user_id")
    private long userId;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

}
