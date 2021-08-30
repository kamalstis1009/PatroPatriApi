package com.patropatri.api.repositories;

import com.patropatri.api.models.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation, Long> {

    @Query("SELECT u FROM PoliceStation u WHERE u.districtId = :districtId")
    List<PoliceStation> getAllPoliceStationsByDistrictId(@Param("districtId") long districtId);

}
