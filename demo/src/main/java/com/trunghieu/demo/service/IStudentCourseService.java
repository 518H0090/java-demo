package com.trunghieu.demo.service;

import com.trunghieu.demo.dto.StudentCourseIdDto;
import com.trunghieu.demo.dto.StudentCourseDto;
import com.trunghieu.demo.dto.StudentIncludeCourseDto;
import com.trunghieu.demo.entity.Course;
import com.trunghieu.demo.entity.Student;

import java.util.List;

public interface IStudentCourseService {
    void addStudentCourse(StudentCourseIdDto request);
    void deleteStudentCourse(StudentCourseIdDto request);
    StudentCourseDto getStudentCourse(StudentCourseIdDto request);
    List<StudentIncludeCourseDto> findAllStudentsWithCourse();
}
