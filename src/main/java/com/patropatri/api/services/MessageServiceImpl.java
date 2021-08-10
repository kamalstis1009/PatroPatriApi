package com.patropatri.api.services;

import com.patropatri.api.models.Inbox;
import com.patropatri.api.models.Notification;
import com.patropatri.api.repositories.InboxRepository;
import com.patropatri.api.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    //Pagination and Sorting using Spring Data JPA
    public Page<Notification> getAllPaginatedNotificationsByUserId(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        //return this.mNotificationRepository.findAll(where(customerHasBirthday()).and(isLongTermCustomer()));
        //return this.mNotificationRepository.findAll(pageable);
        return this.mNotificationRepository.findAll(pageable);
    }

    public List<Notification> findByUserId(int pageNo, int rowSize, long userId) {
        Pageable pageable = PageRequest.of(pageNo - 1, rowSize);
        return this.mNotificationRepository.findByUserId(userId, pageable);
    }

    //======================================================| Inbox
    public List<Inbox> getAllInboxesByUserId(long userId) {
        return mInboxRepository.getAllInboxesByUserId(userId);
    }



}
