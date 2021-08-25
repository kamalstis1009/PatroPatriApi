package com.patropatri.api.repositories;

import com.patropatri.api.models.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM messages WHERE sender_id = :senderId OR receiver_id = :receiverId ORDER BY created_at", nativeQuery = true)
    List<Message> getAllMessages(@Param("senderId") long senderId, @Param("receiverId") long receiverId, Pageable pageable);

}
