package com.example.questifysharedapi.service;

import com.example.questifysharedapi.dto.AnswerRecordDTO;
import com.example.questifysharedapi.dto.QuestionRecordDTO;
import com.example.questifysharedapi.model.Answer;
import com.example.questifysharedapi.model.Question;
import com.example.questifysharedapi.repository.AnswerRepository;
import com.example.questifysharedapi.repository.QuestionRepository;
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

    public QuestionService(QuestionRepository questionRepository,AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Transactional
    public Question saveQuestion(QuestionRecordDTO questionRecordDTO){
        Question question = new Question();
        question.setStatement(questionRecordDTO.statement());
        Question questionSaved = questionRepository.save(question);
        List<Answer> answers = questionRecordDTO.answersRecords().stream().map(answerDTO -> {
            Answer answer = new Answer();
            answer.setText(answerDTO.text());
            answer.setIsCorrect(answerDTO.isCorrect());
            answer.setQuestion(questionSaved); // Relacionar a resposta com a questão
            return answerRepository.save(answer); // Salvar cada resposta
        }).collect(Collectors.toList());
        log.info("Quantidade de Respostas {}" , answers.size());
        questionSaved.setAnswers(answers);
        return questionSaved;
    }

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }


}
