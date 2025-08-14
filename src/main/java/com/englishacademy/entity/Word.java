package com.englishacademy.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "words")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long topicId;
    String word;
    String meaning;
    String example;
    String pronunciation;
    String audioUrl;
    String imageUrl;
}
