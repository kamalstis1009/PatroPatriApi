package com.patropatri.api.services;

import com.patropatri.api.models.*;
import com.patropatri.api.repositories.InboxRepository;
import com.patropatri.api.repositories.MessageRepository;
import com.patropatri.api.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private InboxRepository mInboxRepository;

    @Autowired
    private NotificationRepository mNotificationRepository;

    @Autowired
    private MessageRepository mMessageRepository;

    //======================================================| Notification
    @Override
    public List<NotificationImpl> getAllNotificationsByUserId(int pageNo, int rowSize, long userId) {
        Pageable pageable = PageRequest.of(pageNo - 1, rowSize);
        return this.mNotificationRepository.getAllNotificationsByUserId(userId, pageable);
    }

    @Override
    public Notification addNotification(Notification item) {
        return mNotificationRepository.save(item);
    }

    @Override
    public int deleteNotificationById(long id) {
        boolean isFound = mNotificationRepository.existsById(id);
        if(isFound) {
            mNotificationRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    //======================================================| Inbox
    @Override
    public List<Inbox> getAllInboxesByUserId(int pageNo, int rowSize, long userId) {
        Pageable pageable = PageRequest.of(pageNo - 1, rowSize);
        return mInboxRepository.getAllInboxesByUserId(userId, pageable);
    }

    @Override
    public Inbox addInbox(InboxDto item) {
        Optional<Inbox> response = mInboxRepository.findById(item.getSenderId());
        if(response.isPresent()) {
            Inbox model = mInboxRepository.save(extracted(item, response.get()));
            return addMessage(item, model);
        }
        Inbox model = mInboxRepository.save(extracted(item, new Inbox()));
        return addMessage(item, model);
    }

    private Inbox extracted(InboxDto item, Inbox model) {
        model.setIsEnabled(false);
        if (item.getMessage() != null) {
            model.setMessage(item.getMessage());
        }
        if (item.getReceiverId() != null) {
            model.setReceiverId(item.getReceiverId());
        }
        if (item.getSenderId() != null) {
            model.setSenderId(item.getSenderId());
        }
        return model;
    }

    public Inbox addMessage(InboxDto item, Inbox inbox) {
        Message model = new Message();
        model.setMessage(item.getMessage());
        model.setImageFiles(item.getImageFiles());
        model.setInboxId(inbox.getId());
        mMessageRepository.save(model);
        return inbox;
    }

    @Override
    public int deleteInboxById(long id) {
        boolean isFound = mInboxRepository.existsById(id);
        if(isFound) {
            mInboxRepository.deleteById(id);
            return 1;
        }
        return 0;
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
