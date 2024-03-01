package com.example.BlogAPi.Controller;

import com.example.BlogAPi.Entity.FileResponse;
import com.example.BlogAPi.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Value("${project.image}")
    public String path;

    // above is forget and also forget that we file send or save using RequestParam
    // not imagin like user send path also where to save or we hardcoded.
    @PostMapping("/saveimage")
    // in postman give file name in parameter as file.
    public ResponseEntity<FileResponse> saveFile(@RequestParam MultipartFile file) {
        String fileName = null; // why this doing dont know
        try {
            // now we dont need 2 parameter we get paramter from @value attribute
            fileName = imageService.uploadImage(path, file);

        } catch (IOException e) {
            e.printStackTrace();
            //remail to forget if exception occured then what we need to return
            return new ResponseEntity<FileResponse>(new FileResponse(null, "File was not upload "), HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<FileResponse>(new FileResponse(fileName, "File Save-Upload successfully"), HttpStatus.CREATED);
    }

    @PostMapping("/display")
    private String display(@RequestParam String file)
    {
        System.out.println(file);
        return file;
    }
}
