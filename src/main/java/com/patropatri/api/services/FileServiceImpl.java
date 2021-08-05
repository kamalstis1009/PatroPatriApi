package com.patropatri.api.services;

import com.patropatri.api.models.Gallery;
import com.patropatri.api.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private GalleryRepository mRepository;

    @Override
    public Gallery uploadFile(MultipartFile file, long userId) {
        if (file != null) {
            Path path = Paths.get("uploads").toAbsolutePath().normalize();
            try {
                if (!Files.exists(path)) {
                    Files.createDirectory(path); //Create uploads directory
                }
                //String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String fileName = "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date()) + ".jpg";
                Path targetPath = path.resolve(fileName);
                Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

                //Store to database
                Gallery item = new Gallery();
                item.setImageName(fileName);
                item.setUserId(userId);
                item.setCreatedAt(item.getCreatedAt());
                return mRepository.save(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*Gallery item = mRepository.getGalleryByImageName(imageName);
            if (item == null) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                if (fileName.contains("..")) {
                    //Exception
                }
                String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            } else {
            }*/
        }
        return null;
    }

    @Override
    public Resource downloadFile(String fileName) {
        try {
            Path path = Paths.get("uploads").toAbsolutePath().normalize();
            Path filePath = path.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long deleteAndUploadFile(MultipartFile file, String fileName) {
        if (file != null) {
            Path path = Paths.get("uploads").toAbsolutePath().normalize();
            try {
                if (!Files.exists(path)) {
                    Files.createDirectory(path); //Create uploads directory
                }
                File file2 = new File(path +"/"+ fileName);
                boolean isDeleted = file2.delete();
                if(isDeleted) {
                    System.out.println("File deleted successfully");
                } else {
                    System.out.println("File doesn't exist");
                }
                String fileName2 = "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date()) + ".jpg";
                Path targetPath = path.resolve(fileName2);
                return Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

}
