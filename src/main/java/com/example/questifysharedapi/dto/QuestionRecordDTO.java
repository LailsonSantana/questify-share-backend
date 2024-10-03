package com.example.questifysharedapi.dto;

import com.example.questifysharedapi.model.Answer;

import java.util.List;
import java.util.Set;

public record QuestionRecordDTO(Long id , String statement,
                                List<AnswerRecordDTO> answersRecords , Long userId) {
}
