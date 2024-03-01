package com.example.BlogAPi.Service.Repository;

import com.example.BlogAPi.Entity.Posts;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

}
