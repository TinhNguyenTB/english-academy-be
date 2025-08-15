package com.englishacademy.mapper;

import com.englishacademy.dto.request.UserFlashcardRequest;
import com.englishacademy.dto.response.UserFlashcardResponse;
import com.englishacademy.entity.UserFlashcard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserFlashcardMapper extends GenericMapper<UserFlashcardRequest, UserFlashcardResponse, UserFlashcard>{
}
