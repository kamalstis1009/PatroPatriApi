package com.patropatri.api.repositories;

import com.patropatri.api.models.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

    //@Query(value = "SELECT * FROM Family u WHERE u.created_by = :userId", nativeQuery = true)

    @Query("SELECT u FROM Family u WHERE u.userId = :userId")
    List<Family> getAllFamiliesByUserId(@Param("userId") long userId);

}
