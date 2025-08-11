package com.englishacademy.entity;


import com.englishacademy.enums.EmailStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "emails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email_from")
    private String from;

    @Column(name="email_to")
    private String to;

    @Column(name="subject")
    private String subject;

    @Column(name="content", columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private EmailStatusEnum status;

    @Column(name="retry_num")
    private int retryNum;

    @Column(name="create_at")
    private LocalDateTime createAt;

    @Column(name="last_try_at")
    private LocalDateTime lastTryAt;
}
