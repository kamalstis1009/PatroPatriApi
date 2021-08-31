package com.patropatri.api.repositories;

import com.patropatri.api.models.Comment;
import com.patropatri.api.models.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT A.id, A.message, B.full_name as fullName, B.image_name as imageName, B.id as userId, A.created_at as createdAt "+
            "FROM comments A "+
            "LEFT JOIN users B "+
            "ON A.created_by = B.id "+
            "WHERE A.user_id = :userId "+
            "ORDER BY A.created_at DESC", nativeQuery = true)
    List<CommentDto> getAllCommentsByUserId(@Param("userId") long userId);

}
