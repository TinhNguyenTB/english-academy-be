package com.englishacademy.dto.response;

import lombok.Data;

@Data
public class TopicResponseDTO {
    private Long id;
    private String name;
    private String description;
    private int orderIndex;
    private double price;
    private boolean free;
}