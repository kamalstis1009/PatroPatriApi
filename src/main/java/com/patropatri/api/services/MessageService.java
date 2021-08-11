package com.patropatri.api.services;

import com.patropatri.api.models.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService {

    List<INotification> getAllNotificationsByUserId(int pageNo, int rowSize, long userId);
    Notification addNotification(Notification item);
    int deleteNotificationById(long id);

    List<IInbox> getAllInboxesByUserId(int pageNo, int rowSize, long userId);
    Inbox addInbox(InboxDto item);
    int deleteInboxById(long id);


    Page<Notification> findByUserId(int pageNo, int pageSize, String sortField, String sortDirection);

}
