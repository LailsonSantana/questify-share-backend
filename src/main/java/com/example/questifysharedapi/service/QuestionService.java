package com.example.questifysharedapi.service;

import com.example.questifysharedapi.dto.AnswerRecordDTO;
import com.example.questifysharedapi.dto.QuestionRecordDTO;
import com.example.questifysharedapi.model.Answer;
import com.example.questifysharedapi.model.Question;
import com.example.questifysharedapi.repository.AnswerRepository;
import com.example.questifysharedapi.repository.QuestionRepository;
import com.example.questifysharedapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Question saveQuestion(QuestionRecordDTO questionRecordDTO){
        Question question = new Question();
        question.setStatement(questionRecordDTO.statement());
        question.setUser(userRepository.findById(questionRecordDTO.userId()).get());
        Question questionSaved = questionRepository.save(question);
        List<Answer> answers = questionRecordDTO.answersRecords().stream().map(answerDTO -> {
            Answer answer = new Answer();
            answer.setText(answerDTO.text());
            answer.setIsCorrect(answerDTO.isCorrect());
            answer.setQuestion(questionSaved); // Related the answer at question
            return answerRepository.save(answer); // save the answer
        }).collect(Collectors.toList());
        questionSaved.setAnswers(answers);
        return questionSaved;
    }

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }


}
