package com.englishacademy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class TopicRequestDTO {

    @NotBlank(message = "{topic.name.not.blank}")
    private String name;

    private String description;

    @Min(value = 1, message = "{topic.orderIndex.must.greater.or.equal.to.one}")
    private int orderIndex;

    @PositiveOrZero(message = "{topic.price.must.greater.or.equal.to.zero}")
    private double price;

    private boolean isFree;
}
