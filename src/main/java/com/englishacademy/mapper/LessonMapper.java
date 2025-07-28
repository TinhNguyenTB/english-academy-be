package com.englishacademy.mapper;

import com.englishacademy.dto.request.LessonRequestDTO;
import com.englishacademy.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    LessonRequestDTO toDto(Lesson lesson);

    Lesson toEntity(LessonRequestDTO lessonDto);

    void updateEntityFromDto(LessonRequestDTO lessonDto, @MappingTarget Lesson lesson);
}
