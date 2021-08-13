package com.patropatri.api.repositories;

import com.patropatri.api.models.Liked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikedRepository extends JpaRepository<Liked, Long> {

    @Query("SELECT u FROM Liked u WHERE u.userId = :userId")
    List<Liked> getAllLikesByUserId(@Param("userId") long userId);

}
