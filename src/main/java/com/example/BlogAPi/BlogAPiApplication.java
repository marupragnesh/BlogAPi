package com.example.BlogAPi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAPiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAPiApplication.class, args);
    }

    @Bean
    public ModelMapper entityToDto() {
        return new ModelMapper();
    }

}
