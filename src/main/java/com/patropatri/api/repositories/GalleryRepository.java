package com.patropatri.api.repositories;

import com.patropatri.api.models.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    @Query("SELECT a FROM Gallery a WHERE a.imageName = :imageName")
    Gallery getGalleryByImageName(@Param("imageName") String imageName);

}
