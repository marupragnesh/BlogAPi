package com.example.BlogAPi.ServiceImpl;

import com.example.BlogAPi.Dto.PostDto;
import com.example.BlogAPi.Entity.Posts;
import com.example.BlogAPi.Exception.CustomException;
import com.example.BlogAPi.Service.PostService;
import com.example.BlogAPi.Service.Repository.PostRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    /*@Value("${file.upload-dir}")
    private String filePath;*/
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final PostDto postDto;


    @Autowired
    private ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, PostDto postDto) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.postDto = postDto;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Posts> allPost = postRepository.findAll();
        return allPost.stream().map((PostDto) -> modelMapper.map(allPost, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostDto getOnePost(Long id) {
        Posts posts = postRepository.findById(id).orElseThrow(() -> new CustomException("PostId not Found", "Id", id));
        return modelMapper.map(posts, PostDto.class);
    }

   /* @Override
    public InputStream downloadImage(String path, Long imageId) throws FileNotFoundException {
        Posts posts = postRepository.findById(imageId).orElseThrow(() -> new CustomException("PostId not Found", "Id", imageId));
        String imagePath = posts.getImagePath();
        return new FileInputStream(imagePath);
    }*/

    public PostDto savePost(String postDto1, MultipartFile file, String filePath) throws JsonProcessingException {
        String originalFilename = file.getOriginalFilename();//validation.png

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        JsonNode jsonNode = objectMapper.readTree(postDto1);

        String title = jsonNode.get("title").asText();
        String content = jsonNode.get("content").asText();
        byte[] image = null;
        Posts savePost = null;
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Image is Null");
            }
            image = file.getBytes();
            String fullPath = filePath + originalFilename + formattedDateTime;
            postDto.setTitle(title);
            postDto.setContent(content);
            postDto.setImagepath(fullPath) ;
            Files.write(Paths.get(filePath,originalFilename), image);
            Posts post = modelMapper.map(postDto, Posts.class);
            savePost = postRepository.save(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelMapper.map(savePost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Posts posts = postRepository.findById(id).orElseThrow(() -> new CustomException("PostId not Found", "Id", id));
        posts.setContent(postDto.getContent());
        posts.setTitle(postDto.getTitle());
        posts.setImagePath(postDto.getImagepath());
        Posts updatePost = postRepository.save(posts);
        return modelMapper.map(updatePost, PostDto.class);
    }

    @Override
    public Boolean deletePost(Long id) {
        Posts posts = postRepository.findById(id).orElseThrow(() -> new CustomException("PostId not Found", "Id", id));
        postRepository.delete(posts);
        return true;
    }
}
