package com.englishacademy.mapper;

import com.englishacademy.dto.request.QuestionRequestDTO;
import com.englishacademy.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question mapToEntity(QuestionRequestDTO questionRequestDTO);
    QuestionRequestDTO mapToDto(Question question);
    void updateEntity(QuestionRequestDTO questionRequestDTO, Question question);
}
