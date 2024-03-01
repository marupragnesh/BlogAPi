package com.example.BlogAPi.ServiceImpl;

import com.example.BlogAPi.Dto.CommentsDto;
import com.example.BlogAPi.Entity.Comments;
import com.example.BlogAPi.Exception.CustomException;
import com.example.BlogAPi.Service.CommentsService;
import com.example.BlogAPi.Service.Repository.CommentsRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private final CommentsRepository commentsRepository;
    @Autowired
    private final ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(CommentsServiceImpl.class);

    public CommentsServiceImpl(CommentsRepository commentsRepository, ModelMapper modelMapper) {
        this.commentsRepository = commentsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CommentsDto> getAllComments() {
        List<Comments> allComments = commentsRepository.findAll();

        return allComments.stream().map(comments -> modelMapper.map(comments, CommentsDto.class)).collect(Collectors.toList());

    }

    @Override
    public CommentsDto getComment(Long id) {
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new CustomException("Comment", "Comment id is not Found :", id));
        return returnEntityDto(comments);

    }

    @Override
    public CommentsDto saveComments(CommentsDto commentsDto) {
        Comments comments = returnEntity(commentsDto);
        Comments saveComments = commentsRepository.save(comments);
        return returnEntityDto(saveComments);

    }

    @Override
    public CommentsDto updateComments(CommentsDto commentsDto,Long id) {
        return null;
    }

    @Override
    public Boolean deleteComment(Long id) {
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new CustomException("CommentId", "id", id));
        commentsRepository.delete(comments);
        return true;
    }

    public Comments returnEntity(CommentsDto commentsDto) {
        return modelMapper.map(commentsDto, Comments.class);
    }

    public CommentsDto returnEntityDto(Comments comments) {
        return modelMapper.map(comments, CommentsDto.class);
    }
}
