package com.englishacademy.mapper;

import com.englishacademy.dto.request.LessonProgressRequest;
import com.englishacademy.dto.response.LessonProgressResponse;
import com.englishacademy.entity.LessonProgress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonProgressMapper extends GenericMapper<LessonProgressRequest, LessonProgressResponse, LessonProgress>{
}
