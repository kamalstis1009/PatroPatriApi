package com.patropatri.api.repositories;

import com.patropatri.api.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT u FROM Comment u WHERE u.userId = :userId")
    List<Comment> getAllCommentsByUserId(@Param("userId") long userId);

}
