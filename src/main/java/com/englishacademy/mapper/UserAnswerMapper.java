package com.englishacademy.mapper;

import com.englishacademy.dto.request.UserAnswerRequestDTO;
import com.englishacademy.entity.UserAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserAnswerMapper {

    UserAnswer toUserAnswer(UserAnswerRequestDTO userAnswer);

    UserAnswerRequestDTO toUserAnswerRequestDTO(UserAnswer userAnswer);

    void updateUserAnswerFromRequestDTO(UserAnswerRequestDTO userAnswerRequestDTO, @MappingTarget UserAnswer userAnswer);

}
