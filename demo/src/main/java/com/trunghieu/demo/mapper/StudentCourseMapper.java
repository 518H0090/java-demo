package com.trunghieu.demo.mapper;

import com.trunghieu.demo.dto.StudentCourseDto;
import com.trunghieu.demo.dto.StudentIncludeCourseDto;
import com.trunghieu.demo.entity.Course;
import com.trunghieu.demo.entity.Student;

import java.util.stream.Collectors;

public class StudentCourseMapper {
    public static StudentIncludeCourseDto mapToStudentIncludeCourseDto(Student student) {
        return new StudentIncludeCourseDto(
                student.getId(),
                student.getName(),
                student.getAddress(),
                student.getPhone(),
                student.getCourses().stream().map(CourseMapper::mapToCourseDto).collect(Collectors.toList())
        );
    }

    public static StudentCourseDto mapToStudentIncludeCourseDto(Student student, Course course) {
        var studentDto = StudentMapper.mapToStudentDto(student);
        var CourseDto = CourseMapper.mapToCourseDto(course);

        return new StudentCourseDto(studentDto, CourseDto);
    }
}
