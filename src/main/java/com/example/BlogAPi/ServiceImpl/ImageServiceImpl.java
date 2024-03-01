package com.example.BlogAPi.ServiceImpl;

import com.example.BlogAPi.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //get file name
        String name = file.getOriginalFilename();
        // create path -
        String filePath = path + File.separator+name;
        // create folder if not exist
        File f = new File(path);
        if(!f.exists())
        {
            f.mkdir();
        }
        // save file - upload file
        InputStream inputStream = file.getInputStream();
        Path path1 = Paths.get(filePath);
        System.out.println("inputStream "+ inputStream);
        System.out.println("path1" + path1);
        long copy = Files.copy(inputStream, path1);
        System.out.println("copy" + copy);
        return name;
    }
}
