package com.englishacademy.mapper;

import org.mapstruct.MappingTarget;

public interface GenericMapper<Req, Res, Entity> {
    Entity toEntity(Req request);
    Res toResponse(Entity entity);
    void updateEntity(@MappingTarget Entity entity, Req request);
}