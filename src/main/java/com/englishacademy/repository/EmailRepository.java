package com.englishacademy.repository;

import com.englishacademy.entity.EmailEntity;
import com.englishacademy.enums.EmailStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> {

    Page<EmailEntity> findEmailByStatus(String status, Pageable pageable);

    List<EmailEntity> findEmailByStatusInAndRetryNumLessThan(List<EmailStatusEnum> status, int retryNum);
}
