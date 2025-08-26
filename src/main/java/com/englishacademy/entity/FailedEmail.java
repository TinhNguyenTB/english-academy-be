package com.englishacademy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Immutable
@Subselect("SELECT * FROM failed_mail")
public class FailedEmail {

    @Id
    private Long id;

    @Column(name="email_to")
    private String to;

    @Column(name="subject")
    private String subject;

    @Column(name="body", columnDefinition = "TEXT")
    private String body;

    @Column(name="retry_number")
    private int retryNumber;

    @Column(name="create_at")
    private LocalDateTime createdAt;

    @Column(name="last_retry_time")
    private LocalDateTime lastRetryTime;
}
