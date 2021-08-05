package com.patropatri.api.controllers;

import com.patropatri.api.models.Gallery;
import com.patropatri.api.services.FileServiceImpl;
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

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    private FileServiceImpl mService;

    @PostMapping("/upload")
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
            // Try to determine file's content type
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if(contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @PostMapping("/deleteAndUpload")
    public ResponseEntity<Long> deleteAndUploadFile(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        return new ResponseEntity<>(mService.deleteAndUploadFile(file, fileName), HttpStatus.CREATED);
    }

}
