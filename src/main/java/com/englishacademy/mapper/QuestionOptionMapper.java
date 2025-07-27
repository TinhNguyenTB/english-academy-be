package com.englishacademy.mapper;

import com.englishacademy.dto.request.QuestionOptionRequestDTO;
import com.englishacademy.entity.QuestionOption;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionOptionMapper {

    QuestionOption mapToEntity(QuestionOptionRequestDTO questionOptionRequestDTO);

    QuestionOptionRequestDTO mapToDto(QuestionOption questionOption);

    void updateEntity(QuestionOptionRequestDTO questionOptionRequestDTO, QuestionOption questionOption);

}
