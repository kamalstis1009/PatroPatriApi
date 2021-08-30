package com.patropatri.api.repositories;

import com.patropatri.api.models.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {

    @Query("SELECT u FROM Division u WHERE u.countryId = :countryId")
    List<Division> getAllDivisionsByCountryId(@Param("countryId") long countryId);

}
