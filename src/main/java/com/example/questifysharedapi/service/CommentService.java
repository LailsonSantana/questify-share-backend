package com.example.questifysharedapi.service;

import com.example.questifysharedapi.dto.CommentRecordDTO;
import com.example.questifysharedapi.model.Comment;
import com.example.questifysharedapi.model.Question;
import com.example.questifysharedapi.model.User;
import com.example.questifysharedapi.repository.CommentRepository;
import com.example.questifysharedapi.repository.QuestionRepository;
import com.example.questifysharedapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public Comment saveComment(CommentRecordDTO commentRecordDTO){
        Comment comment = new Comment();
        comment.setText(commentRecordDTO.text());
        Question question = questionRepository.findById(commentRecordDTO.questionId()).get();
        User user = userRepository.findById(commentRecordDTO.userId()).get();
        comment.setQuestion(question);
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }
}
