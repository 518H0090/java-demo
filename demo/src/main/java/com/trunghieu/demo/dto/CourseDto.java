package com.trunghieu.demo.dto;

import com.trunghieu.demo.entity.Department;
import com.trunghieu.demo.entity.Instructor;
import com.trunghieu.demo.entity.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    @NotBlank(message = "code must not be empty")
    private String code;

    @NotBlank(message = "title must not be empty")
    @Length(min = 4, max = 50, message = "length of title must be between 4 and 50")
    private String title;

    @NotNull(message = "description must not be null")
    @Length(max = 50, message = "max length of description is 50")
    private String description;

    @NotBlank(message = "departmentCode must not be empty")
    private String departmentCode;

    @NotBlank(message = "instructorId must not be empty")
    @Min(value = 1, message = "instructorId must be greater than 0")
    private Integer instructorId;
}
