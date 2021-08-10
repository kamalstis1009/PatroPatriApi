package com.patropatri.api.services;

import com.patropatri.api.models.Inbox;
import com.patropatri.api.models.Notification;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService {

    List<Notification> getAllNotificationsByUserId(int pageNo, int rowSize, long userId);
    List<Inbox> getAllInboxesByUserId(int pageNo, int rowSize, long userId);


    Page<Notification> findByUserId(int pageNo, int pageSize, String sortField, String sortDirection);

}
