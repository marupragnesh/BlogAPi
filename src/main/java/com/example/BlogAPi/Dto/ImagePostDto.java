package com.example.BlogAPi.Dto;

import java.util.Arrays;

public class ImagePostDto {
    private PostDto postDto;
    private byte[] imageData;

    public ImagePostDto() {
    }

    public ImagePostDto(PostDto postDto, byte[] imageData) {
        this.postDto = postDto;
        this.imageData = imageData;
    }

    public PostDto getPostDto() {
        return postDto;
    }

    public void setPostDto(PostDto postDto) {
        this.postDto = postDto;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @Override
    public String toString() {
        return "ImagePostDto{" +
                "postDto=" + postDto +
                ", imageData=" + Arrays.toString(imageData) +
                '}';
    }
}
