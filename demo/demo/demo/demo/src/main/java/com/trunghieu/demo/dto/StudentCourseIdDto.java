package com.trunghieu.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseIdDto {
    @NotBlank(message = "studentId must not be empty")
    @Min(value = 1, message = "studentId must be greater than 0")
    private int studentId;

    @NotBlank(message = "courseCode must not be empty")
    private String courseCode;
}
