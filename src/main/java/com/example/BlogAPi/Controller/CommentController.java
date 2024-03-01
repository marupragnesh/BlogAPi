package com.example.BlogAPi.Controller;

import com.example.BlogAPi.Dto.ApiResponse;
import com.example.BlogAPi.Dto.CommentsDto;
import com.example.BlogAPi.Service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private final CommentsService commentsService;

    public CommentController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/comment")
    public ResponseEntity<List<CommentsDto>> getAllComments() {
        List<CommentsDto> allComments = commentsService.getAllComments();
        return new ResponseEntity<>(allComments, HttpStatus.OK);
    }

    @GetMapping("/comment{id}")
    public ResponseEntity<CommentsDto> getSingleComment(@PathVariable Long id) {
        CommentsDto getSingleComment = commentsService.getComment(id);
        return ResponseEntity.ok(getSingleComment);
    }
//here added

    @PostMapping("/comment")
    public ResponseEntity<CommentsDto> saveComment(@RequestBody CommentsDto commentsDto) {

        CommentsDto saveComment = commentsService.saveComments(commentsDto);
        return new ResponseEntity<>(saveComment, HttpStatus.CREATED);
    }

    @PutMapping("/comment{id}")
    public ResponseEntity<CommentsDto> updateComment(@RequestBody CommentsDto commentsDto, @PathVariable Long id) {
        CommentsDto updateComments = commentsService.updateComments(commentsDto, id);
        return new ResponseEntity<>(updateComments, HttpStatus.OK);
    }

    @DeleteMapping("/comment{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long id) {
        commentsService.deleteComment(id);
        return new ResponseEntity<>(new ApiResponse("Delete Comment", true), HttpStatus.OK);
    }
}
