package com.englishacademy.repository;

import com.englishacademy.entity.FailedEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedEmailRepository extends JpaRepository<FailedEmail, Long> {
}
