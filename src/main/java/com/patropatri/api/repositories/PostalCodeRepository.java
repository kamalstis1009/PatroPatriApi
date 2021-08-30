package com.patropatri.api.repositories;

import com.patropatri.api.models.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCode, Long> {

    @Query("SELECT u FROM PostalCode u WHERE u.districtId = :districtId")
    List<PostalCode> getAllPostalCodesByDistrictId(@Param("districtId") long districtId);

}
