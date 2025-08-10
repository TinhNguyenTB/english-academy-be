package com.englishacademy.scheduler;

import com.englishacademy.entity.EmailEntity;
import com.englishacademy.enums.EmailStatusEnum;
import com.englishacademy.repository.EmailRepository;
import com.englishacademy.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailRetryScheduler {

    private final EmailRepository emailRepository;
    private final EmailService emailService;

    @Scheduled(fixedDelay=5000)
    public void reTrySend(){
        log.info("Scheduler reTrySend() running...");
        List<EmailStatusEnum>  emailStatusEnumList = List.of(EmailStatusEnum.FAILED, EmailStatusEnum.PENDING );

        List<EmailEntity> retryList = emailRepository.findEmailByStatusInAndRetryNumLessThan(emailStatusEnumList, 5);
        log.info("Emails to retry count: {}", retryList.size());
        for(EmailEntity emailEntity : retryList){
            emailService.sendEmailGrid(emailEntity);
        }

    }

}
