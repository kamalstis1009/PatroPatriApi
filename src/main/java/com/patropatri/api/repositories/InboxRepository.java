package com.patropatri.api.repositories;

import com.patropatri.api.models.Inbox;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InboxRepository extends JpaRepository<Inbox, Long> {

    @Query("SELECT u FROM Inbox u WHERE u.receiverId = :userId")
    public List<Inbox> getAllInboxesByUserId(@Param("userId") long userId, Pageable pageable);

}
