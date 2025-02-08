package com.trunghieu.demo.dto;

import com.trunghieu.demo.entity.Course;
import com.trunghieu.demo.entity.Student;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseDto {
    @Valid
    private StudentDto student;

    @Valid
    private CourseDto course;
}
