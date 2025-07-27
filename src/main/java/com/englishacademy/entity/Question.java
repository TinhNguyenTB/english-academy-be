package com.englishacademy.entity;

import com.englishacademy.enums.OptionType;
import com.englishacademy.enums.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lesson_id", nullable = false)
    private Long lessonId;

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable = false)
    private QuestionType type;

    @Column(name = "prompt", nullable = false)
    private String prompt;

    @Enumerated(EnumType.STRING)
    @Column(name = "option_type", nullable = false)
    private OptionType optionType;

    @Column(name = "correct_answer", nullable = false)
    private String correctAnswer;

    @Column(name = "media_url", nullable = true)
    private String mediaUrl;

    @Column(name = "explanation", nullable = false)
    private String explanation;


}
