package com.englishacademy.mapper;

import com.englishacademy.dto.request.WordRequest;
import com.englishacademy.dto.response.WordResponse;
import com.englishacademy.entity.Word;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WordMapper extends GenericMapper<WordRequest, WordResponse, Word>{
}
