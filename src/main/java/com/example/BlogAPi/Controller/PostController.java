package com.example.BlogAPi.Controller;

import com.example.BlogAPi.Dto.ApiResponse;
import com.example.BlogAPi.Dto.PostDto;
import com.example.BlogAPi.Service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private final PostService postService;
    @Value("${project.image}")
    public String path; //images/
    @Autowired
    private ObjectMapper objectMapper;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> allPosts = postService.getAllPosts();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/post{id}")
    public ResponseEntity<PostDto> getOnePost(@PathVariable Long id) {
        PostDto onePost = postService.getOnePost(id);
        return ResponseEntity.ok(onePost);
    }

/*    @GetMapping(value = "/profile/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageId") Long imageId, HttpServletResponse response) throws IOException {
        InputStream resource = this.postService.downloadImage(path,imageId);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }*/

    @PostMapping("/post")
    public ResponseEntity<PostDto> createPost(@RequestParam("file") MultipartFile file, @RequestParam("postDto") String postDto) throws JsonProcessingException {

        PostDto savePost = postService.savePost(postDto, file, path);
        return new ResponseEntity<>(savePost, HttpStatus.CREATED);
    }

    @PutMapping("/post{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Long id) {
        PostDto savePost = postService.updatePost(postDto, id);
        return new ResponseEntity<>(savePost, HttpStatus.OK);
    }

    @DeleteMapping("/post{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(new ApiResponse("Delete Post", true), HttpStatus.OK);
    }
}
