package com.example.BlogAPi.Dto;

import jakarta.validation.constraints.NotNull;

public class CommentsDto {
    private Long id;
    @NotNull(message = "Kindly Provide Some Comment")
    private String comment;
    private UserDto user;
    private PostDto post;

    public CommentsDto() {
    }

    public CommentsDto(Long id, String comment, UserDto user, PostDto post) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "CommentsDto{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}
