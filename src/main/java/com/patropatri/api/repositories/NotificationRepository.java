package com.patropatri.api.repositories;

import com.patropatri.api.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT u FROM Notification u WHERE u.userId = :userId")
    List<Notification> getAllNotificationsByUserId(@Param("userId") long userId);

}
