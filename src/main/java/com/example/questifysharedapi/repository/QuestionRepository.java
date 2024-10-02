package com.example.questifysharedapi.repository;

import com.example.questifysharedapi.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
