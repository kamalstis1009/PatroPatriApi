package com.patropatri.api.services;

import com.patropatri.api.models.*;
import com.patropatri.api.repositories.*;
import com.patropatri.api.utils.ConstantKey;
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

    @Autowired
    private CommentRepository mCommentRepository;

    @Autowired
    private LikedRepository mLikedRepository;

    @Autowired
    private UserRepository mUserRepository;

    //======================================================| Notification
    @Override
    public List<INotification> getAllNotificationsByUserId(int pageNo, int rowSize, long userId) {
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
    public List<IInbox> getAllInboxesByUserId(int pageNo, int rowSize, long userId) {
        Pageable pageable = PageRequest.of(pageNo - 1, rowSize);
        return mInboxRepository.getAllInboxesByUserId(userId, pageable);
    }

    @Override
    public List<Message> getAllMessages(int pageNo, int rowSize, long userId) {
        Pageable pageable = PageRequest.of(pageNo - 1, rowSize);
        return mMessageRepository.getAllMessages(userId, pageable);
    }

    @Override
    public MessageDto addInbox(MessageDto item) {
        Inbox response = mInboxRepository.getInboxBySenderId(item.getSenderId());
        if(response != null) {
            Inbox inbox = extracted(item, response);
            Inbox model = mInboxRepository.save(inbox);
            return extracted2(item, addMessage(item, model));
        }
        Inbox model = mInboxRepository.save(extracted(item, new Inbox()));
        return extracted2(item, addMessage(item, model));
    }

    private Inbox extracted(MessageDto item, Inbox model) {
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

    private MessageDto extracted2(MessageDto item, Message item2) {
        MessageDto model = new MessageDto();
        model.setMessage(item2.getMessage());
        model.setSenderId(item.getSenderId());
        model.setReceiverId(item.getReceiverId());
        if (item2.getImageFiles() != null) {
            model.setImageFiles(item2.getImageFiles());
        }
        return model;
    }

    public Message addMessage(MessageDto item, Inbox inbox) {
        Message model = new Message();
        model.setMessage(item.getMessage());
        model.setImageFiles(item.getImageFiles());
        model.setInboxId(inbox.getId());
        return mMessageRepository.save(model);
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

    //======================================================| Comment
    @Override
    public List<CommentDto> getAllCommentsByUserId(long userId) {
        //return mCommentRepository.findAll();
        return mCommentRepository.getAllCommentsByUserId(userId);
    }

    @Override
    public Comment addComment(Comment item) {
        Notification model = updateUserLikeComment(item.getUserId(), item.getCreatedBy(), true);
        if (model != null) {
            return mCommentRepository.save(item);
        }
        return null;
    }

    private Notification updateUserLikeComment(long userId, long createdBy, boolean isComment) {
        Optional<User> response = mUserRepository.findById(userId);
        if (response.isPresent()) {
            User model = response.get();
            if (isComment) {
                model.setCommentAmount(model.getCommentAmount() + 1);
            } else {
                model.setLikedAmount(model.getLikedAmount() + 1);
            }
            mUserRepository.save(model);
            Notification m = new Notification();
            if (isComment) {
                m.setTitle(model.getFullName() + ConstantKey.COMMENT_TITLE);
                m.setType(ConstantKey.COMMENT);
            } else {
                m.setTitle(model.getFullName() + ConstantKey.LIKED_TITLE);
                m.setType(ConstantKey.LIKED);
            }
            m.setIsEnabled(false);
            m.setSenderId(createdBy);
            m.setReceiverId(userId);
            return addNotification(m);
        }
        return null;
    }

    @Override
    public int deleteCommentById(long id) {
        boolean isFound = mCommentRepository.existsById(id);
        if(isFound) {
            mCommentRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    //======================================================| Liked
    @Override
    public Liked getLikedByUserId(long userId) {
        return mLikedRepository.getLikedByUserId(userId);
    }

    @Override
    public List<LikedDto> getAllLikesByUserId(long userId) {
        //return mCommentRepository.findAll();
        return mLikedRepository.getAllLikesByUserId(userId);
    }

    @Override
    public Liked addLiked(Liked item) {
        Liked model = getLikedByUserId(item.getCreatedBy());
        if (model == null) {
            Notification mNot = updateUserLikeComment(item.getUserId(), item.getCreatedBy(), false);
            if (mNot != null) {
                return mLikedRepository.save(item);
            }
            return null;
        }
        return null;
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
