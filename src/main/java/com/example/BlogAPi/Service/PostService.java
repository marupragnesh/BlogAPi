package com.example.BlogAPi.Service;

import com.example.BlogAPi.Dto.PostDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto getOnePost(Long id);
//    InputStream downloadImage(String path, Long imageId) throws FileNotFoundException;

    PostDto savePost(String postDto, MultipartFile file, String path) throws JsonProcessingException;

    PostDto updatePost(PostDto postDto, Long id);

    Boolean deletePost(Long id);


}
