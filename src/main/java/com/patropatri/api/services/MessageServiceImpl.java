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
    @Override
    public List<Notification> getAllNotificationsByUserId(int pageNo, int rowSize, long userId) {
        Pageable pageable = PageRequest.of(pageNo - 1, rowSize);
        return this.mNotificationRepository.getAllNotificationsByUserId(userId, pageable);
    }

    //======================================================| Inbox
    @Override
    public List<Inbox> getAllInboxesByUserId(int pageNo, int rowSize, long userId) {
        Pageable pageable = PageRequest.of(pageNo - 1, rowSize);
        return mInboxRepository.getAllInboxesByUserId(userId, pageable);
    }





    //======================================================| Pagination and Sorting using Spring Data JPA
    @Override
    public Page<Notification> findByUserId(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        //return this.mNotificationRepository.findAll(where(customerHasBirthday()).and(isLongTermCustomer()));
        //return this.mNotificationRepository.findAll(pageable);
        return this.mNotificationRepository.findAll(pageable);
    }


}
