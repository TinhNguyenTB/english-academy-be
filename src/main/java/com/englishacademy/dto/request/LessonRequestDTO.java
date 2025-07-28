package com.englishacademy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LessonRequestDTO {

    @NotBlank(message = "Mã bài học không được để trống")
    private String code;

    @NotNull(message = "ID chủ đề không được để trống")
    private Long topicId;

    @NotBlank(message = "Tên bài học không được để trống")
    private String name;

    @Min(value = 1, message = "Số thứ tự không được bé hơn 1")
    private int orderIndex;

    @Min(value = 0, message = "Tổng số câu hỏi không được bé hơn 0")
    private int totalQuestion;

}
