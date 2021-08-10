package com.patropatri.api.services;

import com.patropatri.api.models.Notification;
import org.springframework.data.domain.Page;

public interface MessageService {

    Page<Notification> getAllPaginatedNotificationsByUserId(int pageNo, int pageSize, String sortField, String sortDirection);

}
