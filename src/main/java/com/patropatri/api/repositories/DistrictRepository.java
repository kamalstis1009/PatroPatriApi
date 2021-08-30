package com.patropatri.api.repositories;

import com.patropatri.api.models.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    @Query("SELECT u FROM District u WHERE u.divisionId = :divisionId")
    List<District> getAllDistrictsByDivisionId(@Param("divisionId") long divisionId);

}
