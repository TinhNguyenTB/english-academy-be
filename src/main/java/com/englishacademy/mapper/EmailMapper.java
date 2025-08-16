package com.englishacademy.mapper;

import com.englishacademy.dto.request.EmailMessageDTO;
import com.englishacademy.entity.FailedEmail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    EmailMessageDTO toDto(FailedEmail email);

    FailedEmail toEntity(EmailMessageDTO dto);
}
