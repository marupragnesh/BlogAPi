package com.example.BlogAPi.Service.Repository;

import com.example.BlogAPi.Entity.Comments;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Long> {

}
