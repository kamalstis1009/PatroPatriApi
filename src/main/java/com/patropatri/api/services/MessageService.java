package com.patropatri.api.services;

import com.patropatri.api.models.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService {

    //======================================================| Notification
    List<INotification> getAllNotificationsByUserId(int pageNo, int rowSize, long userId);
    Notification addNotification(Notification item);
    int deleteNotificationById(long id);

    //======================================================| Inbox
    List<IInbox> getAllInboxesByUserId(int pageNo, int rowSize, long userId);
    MessageDto addInbox(MessageDto item);
    int deleteInboxById(long id);

    List<Message> getAllMessages(int pageNo, int rowSize, long userId);

    //======================================================| Comment
    List<CommentDto> getAllCommentsByUserId(long userId);
    Comment addComment(Comment item);
    int deleteCommentById(long id);

    //======================================================| Liked
    Liked getLikedByUserId(long userId);
    List<LikedDto> getAllLikesByUserId(long userId);
    Liked addLiked(Liked item);


    Page<Notification> findByUserId(int pageNo, int pageSize, String sortField, String sortDirection);

}
