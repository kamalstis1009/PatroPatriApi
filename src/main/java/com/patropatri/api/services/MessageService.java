package com.patropatri.api.services;

import com.patropatri.api.models.Notification;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService {

    Page<Notification> getAllPaginatedNotificationsByUserId(int pageNo, int pageSize, String sortField, String sortDirection);
    List<Notification> findByUserId(int pageNo, int pageSize, long userId);

}
