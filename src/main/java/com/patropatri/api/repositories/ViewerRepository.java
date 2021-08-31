package com.patropatri.api.repositories;

import com.patropatri.api.models.LikedDto;
import com.patropatri.api.models.Viewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewerRepository extends JpaRepository<Viewer, Long> {

    @Query("SELECT u FROM Viewer u WHERE u.createdBy = :createdBy")
    Viewer getViewerByUserId(@Param("createdBy") long createdBy);

    @Query(value = "SELECT A.id, B.full_name as fullName, B.image_name as imageName, B.id as userId, A.created_at as createdAt "+
            "FROM viewers A "+
            "LEFT JOIN users B "+
            "ON A.created_by = B.id "+
            "WHERE A.user_id = :userId "+
            "ORDER BY A.created_at", nativeQuery = true)
    List<LikedDto> getAllViewersByUserId(@Param("userId") long userId);

}
