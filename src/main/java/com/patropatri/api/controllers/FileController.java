package com.patropatri.api.controllers;

import com.patropatri.api.models.Gallery;
import com.patropatri.api.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

    @Autowired
    private FileService mService;

    @PostMapping("/upload/gallery-image")
    public ResponseEntity<Gallery> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") long userId) {
        return new ResponseEntity<>(mService.uploadFile(file, userId), HttpStatus.CREATED);
    }

    /*@GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String fileName) {
        Resource file = mService.downloadFile(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }*/

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = mService.downloadFile(fileName);
        try {
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath()); // Try to determine file's content type
            if(contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @PostMapping("/upload/update-image")
    public ResponseEntity<String> deleteAndUploadFile(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        return new ResponseEntity<>(mService.deleteAndUploadFile(file, fileName), HttpStatus.CREATED);
    }

    @PostMapping("/upload/update-profile-cover")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName, @RequestParam("isProfile") boolean isProfile, @RequestParam("userId") long userId) {
        //logger.info(fileName);
        return new ResponseEntity<>(mService.uploadImage(file, fileName, isProfile, userId), HttpStatus.CREATED);
    }

    @PostMapping("/upload/update-files")
    public ResponseEntity<ArrayList<String>> uploadMultiFiles(@RequestParam("file") MultipartFile[] files) {
        return new ResponseEntity<>(mService.uploadMultiFiles(files), HttpStatus.CREATED);
    }

}
