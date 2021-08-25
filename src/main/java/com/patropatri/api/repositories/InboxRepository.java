package com.patropatri.api.repositories;

import com.patropatri.api.models.IInbox;
import com.patropatri.api.models.Inbox;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InboxRepository extends JpaRepository<Inbox, Long> {

    //@Query("SELECT u FROM Inbox u WHERE u.receiverId = :userId")
    //List<Inbox> getAllInboxesByUserId(@Param("userId") long userId, Pageable pageable);

    @Query(value = "SELECT A.id, A.message, A.sender_id as senderId, B.full_name as senderName, B.image_name as senderImage, A.is_enabled as isEnabled, A.created_at as createdAt "+
            "FROM inboxes A "+
            "LEFT JOIN users B "+
            "ON A.receiver_id = B.id "+
            "WHERE A.sender_id = :userId OR receiver_id = :userId "+
            "ORDER BY A.created_at", nativeQuery = true)
    List<IInbox> getAllInboxesByUserId(@Param("userId") long userId, Pageable pageable);

    @Query(value = "SELECT * FROM inboxes A WHERE A.sender_id = :senderId", nativeQuery = true)
    Inbox getInboxBySenderId(@Param("senderId") long senderId);

}
