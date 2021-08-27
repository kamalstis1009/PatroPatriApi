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
    private ViewerRepository mViewerRepository;

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
    public List<Message> getAllMessages(int pageNo, int rowSize, long senderId, long receiverId) {
        Pageable pageable = PageRequest.of(pageNo - 1, rowSize);
        return mMessageRepository.getAllMessages(senderId, receiverId, pageable);
    }

    @Override
    public MessageDto addInbox(MessageDto dto) {
        Inbox response = mInboxRepository.getInboxBySenderId(dto.getSenderId());
        Inbox response2 = mInboxRepository.getInboxByReceiverId(dto.getSenderId());
        if(response != null) {
            Inbox item = mInboxRepository.save(extracted(dto, response, false));
            mInboxRepository.save(extracted(dto, response2, true));
            Message model = addMessage(dto, item.getId());
            if (model != null) {
                return extracted2(dto, model);
            }
            return null;
        }
        Inbox item = mInboxRepository.save(extracted(dto, new Inbox(), false));
        mInboxRepository.save(extracted(dto, new Inbox(), true));
        Message model = addMessage(dto, item.getId());
        if (model != null) {
            return extracted2(dto, model);
        }
        return null;
    }

    private Inbox extracted(MessageDto dto, Inbox model, boolean isReceiver) {
        if (dto.getMessage() != null) {
            model.setMessage(dto.getMessage());
        }
        if (dto.getReceiverId() != null) {
            model.setReceiverId(dto.getReceiverId());
            if (isReceiver)
                model.setReceiverId(dto.getSenderId());
        }
        if (dto.getSenderId() != null) {
            model.setSenderId(dto.getSenderId());
            if (isReceiver)
                model.setSenderId(dto.getReceiverId());
        }
        if (dto.getIsEnabled() != null) {
            model.setIsEnabled(dto.getIsEnabled());
        }
        return model;
    }

    private MessageDto extracted2(MessageDto dto, Message item2) {
        MessageDto model = new MessageDto();
        if (item2.getMessage() != null) {
            model.setMessage(item2.getMessage());
        }
        if (item2.getImageFiles() != null) {
            model.setImageFiles(item2.getImageFiles());
        }
        if (dto.getSenderId() != null) {
            model.setSenderId(dto.getSenderId());
        }
        if (dto.getReceiverId() != null) {
            model.setReceiverId(dto.getReceiverId());
        }
        if (dto.getIsEnabled() != null) {
            model.setIsEnabled(dto.getIsEnabled());
        }
        return model;
    }

    public Message addMessage(MessageDto dto, Long inboxId) {
        Message model = new Message();
        if (dto.getMessage() != null) {
            model.setMessage(dto.getMessage());
        }
        if (dto.getImageFiles() != null) {
            model.setImageFiles(dto.getImageFiles());
        }
        if (dto.getSenderId() != null) {
            model.setSenderId(dto.getSenderId());
        }
        if (dto.getReceiverId() != null) {
            model.setReceiverId(dto.getReceiverId());
        }
        if (dto.getIsEnabled() != null) {
            model.setIsEnabled(dto.getIsEnabled());
        }
        if (inboxId != null) {
            model.setInboxId(inboxId);
        }
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
        Notification model = updateUserLikeComment(item.getUserId(), item.getCreatedBy(), 1);
        if (model != null) {
            return mCommentRepository.save(item);
        }
        return null;
    }

    private Notification updateUserLikeComment(long userId, long createdBy, int type) {
        Optional<User> response = mUserRepository.findById(userId);
        if (response.isPresent()) {
            User model = response.get();
            if (type == 1) {
                model.setCommentAmount(model.getCommentAmount() + 1);
            }
            if (type == 2) {
                model.setLikedAmount(model.getLikedAmount() + 1);
            }
            if (type == 3) {
                model.setViewerAmount(model.getViewerAmount() + 1);
            }
            mUserRepository.save(model);
            Notification m = new Notification();
            if (type == 1) {
                m.setTitle(model.getFullName() + ConstantKey.COMMENT_TITLE);
                m.setType(ConstantKey.COMMENT);
            }
            if (type == 2) {
                m.setTitle(model.getFullName() + ConstantKey.LIKED_TITLE);
                m.setType(ConstantKey.LIKED);
            }
            if (type == 3) {
                m.setTitle(model.getFullName() + ConstantKey.VIEWER_TITLE);
                m.setType(ConstantKey.VIEWER);
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
            Notification mNot = updateUserLikeComment(item.getUserId(), item.getCreatedBy(), 2);
            if (mNot != null) {
                return mLikedRepository.save(item);
            }
            return null;
        }
        return null;
    }

    //======================================================| Visitor
    @Override
    public Viewer addViewed(Viewer item) {
        Viewer model = mViewerRepository.getViewerByUserId(item.getCreatedBy());
        if (model == null) {
            Notification mNot = updateUserLikeComment(item.getUserId(), item.getCreatedBy(), 3);
            if (mNot != null) {
                return mViewerRepository.save(item);
            }
            return null;
        }
        return null;
    }

    @Override
    public List<LikedDto> getAllViewersByUserId(long userId) {
        //return mCommentRepository.findAll();
        return mViewerRepository.getAllViewersByUserId(userId);
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
