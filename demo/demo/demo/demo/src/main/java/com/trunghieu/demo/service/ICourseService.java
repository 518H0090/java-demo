package com.trunghieu.demo.service;

import com.trunghieu.demo.dto.AddCourseDto;
import com.trunghieu.demo.dto.AddStudentDto;
import com.trunghieu.demo.dto.CourseDto;
import com.trunghieu.demo.dto.StudentDto;

import java.util.List;

public interface ICourseService {
    CourseDto addCourse(AddCourseDto courseDto);
    void updateCourse(CourseDto courseDto);
    CourseDto getCourseByCode(String code);
    List<CourseDto> getCourses();
    void deleteCourse(String code);
}
