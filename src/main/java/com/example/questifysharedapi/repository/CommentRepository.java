package com.example.questifysharedapi.repository;

import com.example.questifysharedapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
