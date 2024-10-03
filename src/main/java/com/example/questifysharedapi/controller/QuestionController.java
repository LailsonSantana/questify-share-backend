package com.example.questifysharedapi.controller;



import com.example.questifysharedapi.dto.QuestionRecordDTO;

import com.example.questifysharedapi.model.Question;
import com.example.questifysharedapi.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
@Slf4j // Annotation to log creation
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> saveQuestion(@RequestBody QuestionRecordDTO questionRecordDTO){
        try{
            log.info("THE METHOD CREATE WITH WAS CALLED ");
            return ResponseEntity.status(HttpStatus.CREATED).body(questionService.saveQuestion(questionRecordDTO));
        }catch (Exception e){
            log.info("ERROR : {} " ,e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions(){
        List<Question> questions = new ArrayList<>();
        questions = questionService.getAllQuestions();
        for(Question q : questions){
            log.info("Respostas {}" , q.getAnswers().size());
        }
        return ResponseEntity.ok(questionService.getAllQuestions());
    }
}
