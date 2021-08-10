package com.patropatri.api.services;

import com.patropatri.api.models.Inbox;
import com.patropatri.api.models.Notification;
import com.patropatri.api.repositories.InboxRepository;
import com.patropatri.api.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private InboxRepository mInboxRepository;

    @Autowired
    private NotificationRepository mNotificationRepository;

    //======================================================| Notification
    public List<Notification> getAllNotificationsByUserId(long userId) {
        return mNotificationRepository.getAllNotificationsByUserId(userId);
    }

    //======================================================| Inbox
    public List<Inbox> getAllInboxesByUserId(long userId) {
        return mInboxRepository.getAllInboxesByUserId(userId);
    }

}
