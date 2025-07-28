package com.englishacademy.mapper;

import com.englishacademy.dto.request.QuestionOptionRequestDTO;
import com.englishacademy.entity.QuestionOption;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface QuestionOptionMapper {

    QuestionOption mapToEntity(QuestionOptionRequestDTO questionOptionRequestDTO);

    QuestionOptionRequestDTO mapToDto(QuestionOption questionOption);

    void updateEntity(QuestionOptionRequestDTO questionOptionRequestDTO, @MappingTarget QuestionOption questionOption);

}
