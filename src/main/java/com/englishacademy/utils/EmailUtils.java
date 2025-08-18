package com.englishacademy.utils;

import com.englishacademy.dto.request.EmailMessageDTO;
import com.englishacademy.mapper.EmailMapper;
import com.englishacademy.repository.FailedEmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Log4j2
@RequiredArgsConstructor
@Component
public class EmailUtils {

    private final FailedEmailRepository failedEmailRepository;
    private final EmailMapper emailMapper;

    public boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    }
    public void saveFailedEmail(String to, String subject, String content) {
        EmailMessageDTO emailMessageDTO = new EmailMessageDTO();
        emailMessageDTO.setTo(to);
        emailMessageDTO.setSubject(subject);
        emailMessageDTO.setBody(content);
        emailMessageDTO.setRetryNumber(0);
        emailMessageDTO.setCreateAt(LocalDateTime.now());
        emailMessageDTO.setLastRetryTime(LocalDateTime.now());

        failedEmailRepository.save(emailMapper.toEntity(emailMessageDTO));
        log.info("Saved failed email to DB: {} (reason: {})", to);
    }
}
