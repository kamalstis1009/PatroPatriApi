package com.patropatri.api.repositories;

import com.patropatri.api.models.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

    @Query("SELECT u FROM Education u WHERE u.userId = :userId")
    List<Education> getAllEducationsByUserId(@Param("userId") long userId);

}
