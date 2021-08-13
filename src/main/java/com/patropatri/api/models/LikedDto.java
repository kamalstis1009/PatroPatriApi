package com.patropatri.api.models;

import lombok.Data;
import java.util.Date;

@Data
public class LikedDto {

    private Long id;
    private String fullName;
    private String imageName;
    private Long userId;
    private Date createdAt;

}
