package com.englishacademy.mapper;

import com.englishacademy.dto.request.TopicRequestDTO;
import com.englishacademy.entity.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    Topic toEntity(TopicRequestDTO topicRequestDTO);

    TopicRequestDTO toDto(Topic topic);

    void updateEntityFromDto(TopicRequestDTO topicRequestDTO, @MappingTarget Topic topic);

}
