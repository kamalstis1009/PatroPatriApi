package com.patropatri.api.services;

import com.patropatri.api.models.Gallery;

import java.util.List;

public interface GalleryService {

    //Gallery addGallery(Gallery item);
    //Gallery getGalleryById(long id);
    List<Gallery> getAllGalleriesByUserId(long userId);
    //Gallery updateGallery(Gallery item);
    int deleteGalleryById(long id, String fileName);

}
