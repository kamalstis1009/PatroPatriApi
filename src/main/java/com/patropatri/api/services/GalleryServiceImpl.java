package com.patropatri.api.services;

import com.patropatri.api.models.Gallery;
import com.patropatri.api.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryRepository mRepository;

    @Override
    public List<Gallery> getAllGalleriesByUserId(long userId) {
        return mRepository.getAllGalleriesByUserId(userId);
    }

    @Override
    public int deleteGalleryById(long id) {
        boolean isFound = mRepository.existsById(id);
        if(isFound) {
            mRepository.deleteById(id);
            return 1;
        }
        return 0;
    }
}
