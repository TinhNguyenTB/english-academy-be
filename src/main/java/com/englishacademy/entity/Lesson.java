package com.englishacademy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "topic_id", nullable = false)
    private Long topic_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "order_index", nullable = false)
    private int order_index;

    @Column(name="total_question", nullable = false)
    private int total_question;


}
