package com.patropatri.api.services;

import com.patropatri.api.models.Gallery;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    boolean deleteFile(String fileName);

    Gallery uploadFile(MultipartFile file, long userId);
    Resource downloadFile(String fileName);

    String deleteAndUploadFile(MultipartFile file, String fileName);
    String uploadImage(MultipartFile file, String fileName, boolean isProfile, long id);
}
