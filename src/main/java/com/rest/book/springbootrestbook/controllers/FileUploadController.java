package com.rest.book.springbootrestbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.book.springbootrestbook.helper.FileUploadHelper;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            // System.out.println(file.getOriginalFilename());
            // System.out.println(file.getSize());
            // System.out.println(file.getContentType());
            // System.out.println(file.getName());
            // System.out.println(file.toString());

            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file....");
            }

            // if (file.getContentType().equals("image/jpeg")) {
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request
            // only jpeg file....");
            // }

            // now uploading file
            boolean res = fileUploadHelper.uploadFile(file);
            if (res) {
                // return ResponseEntity.ok("File uploaded successfully");

                // to get the path of image file in our rersponse
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/")
                        .path(file.getOriginalFilename()).toUriString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.Try again..........");
    }
}
