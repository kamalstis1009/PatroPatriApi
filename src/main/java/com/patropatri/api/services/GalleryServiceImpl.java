package com.patropatri.api.services;

import com.patropatri.api.models.Gallery;
import com.patropatri.api.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryRepository mRepository;

    @Autowired
    private FileServiceImpl mService;

    @Override
    public List<Gallery> getAllGalleriesByUserId(long userId) {
        return mRepository.getAllGalleriesByUserId(userId);
    }

    @Override
    public int deleteGalleryById(long id, String fileName) {
        boolean isFound = mRepository.existsById(id);
        if(isFound) {
            mRepository.deleteById(id);
            mService.deleteFile(fileName);
            return 1;
        }
        return 0;
    }
}
