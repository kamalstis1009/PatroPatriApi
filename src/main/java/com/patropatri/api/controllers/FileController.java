package com.patropatri.api.controllers;

import com.patropatri.api.models.Gallery;
import com.patropatri.api.services.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    private FileServiceImpl mService;

    @PostMapping("/upload")
    public ResponseEntity<Gallery> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") long userId) {
        return new ResponseEntity<>(mService.uploadFile(file, userId), HttpStatus.CREATED);
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String fileName) {
        Resource file = mService.downloadFile(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
