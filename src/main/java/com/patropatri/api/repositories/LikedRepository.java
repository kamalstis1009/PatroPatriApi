package com.patropatri.api.repositories;

import com.patropatri.api.models.Liked;
import com.patropatri.api.models.LikedDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikedRepository extends JpaRepository<Liked, Long> {

    @Query("SELECT u FROM Liked u WHERE u.createdBy = :userId")
    Liked getLikedByUserId(@Param("createdBy") long userId);

    @Query(value = "SELECT A.id, B.full_name as fullName, B.image_name as imageName, B.id as userId, A.created_at as createdAt "+
            "FROM likes A "+
            "LEFT JOIN users B "+
            "ON A.userId = B.id "+
            "WHERE A.userId = :userId "+
            "ORDER BY A.created_at", nativeQuery = true)
    List<LikedDto> getAllLikesByUserId(@Param("userId") long userId);

}
