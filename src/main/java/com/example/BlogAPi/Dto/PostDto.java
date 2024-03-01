package com.example.BlogAPi.Dto;


import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class PostDto {
    private Long Id;
    @NotNull(message = "Title could not be null")
    private String title;
    @NotNull(message = "Kindly Provide Some Content")
    private String content;
    private String imagepath;

    public PostDto() {
    }

    public PostDto(String title, String content, String imagepath) {
        this.title = title;
        this.content = content;
        this.imagepath = imagepath;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imagepath='" + imagepath + '\'' +
                '}';
    }
}
