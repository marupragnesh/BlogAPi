package com.example.BlogAPi.Service;

import com.example.BlogAPi.Dto.CommentsDto;
import com.example.BlogAPi.Service.Repository.CommentsRepository;

import java.util.List;

public interface CommentsService  {

    List<CommentsDto> getAllComments();
    CommentsDto getComment(Long id);
    CommentsDto saveComments(CommentsDto commentsDto);
    CommentsDto updateComments(CommentsDto commentsDto,Long id);
    Boolean deleteComment(Long id);

}
