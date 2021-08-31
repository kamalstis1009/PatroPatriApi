package com.patropatri.api.repositories;

import com.patropatri.api.models.Notification;
import com.patropatri.api.models.INotification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /*
    SELECT A.id, A.title, A.type, A.sender_id as senderId, B.full_name as senderName, B.image_name as senderImage, A.is_enabled as isEnabled, A.created_at as createdAt
    FROM patropatri.notifications A
    LEFT JOIN patropatri.users B
    ON A.receiver_id = B.id
    WHERE A.receiver_id = 1
    ORDER BY A.created_at;
    */

    //@Query("SELECT u FROM Notification u WHERE u.receiverId = :userId")
    //public List<Notification> getAllNotificationsByUserId(@Param("userId") long userId, Pageable pageable);

    @Query(value = "SELECT A.id, A.title, A.type, A.sender_id as senderId, B.full_name as senderName, B.image_name as senderImage, A.is_enabled as isEnabled, A.created_at as createdAt "+
            "FROM notifications A "+
            "LEFT JOIN users B "+
            "ON A.sender_id = B.id "+
            "WHERE A.receiver_id = :userId "+
            "ORDER BY A.created_at", nativeQuery = true)
    List<INotification> getAllNotificationsByUserId(@Param("userId") long userId, Pageable pageable);

}
