package com.englishacademy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class TopicRequestDTO {

    @NotBlank(message = "Không được để trống tên chủ đề")
    private String name;

    private String description;

    @Min(value = 1, message = "Chỉ số thứ tự phải lớn hơn hoặc bằng 1")
    private int orderIndex;

    @PositiveOrZero(message = "Giá phải là số dương hoặc bằng 0")
    private double price;

    private boolean isFree;
}
