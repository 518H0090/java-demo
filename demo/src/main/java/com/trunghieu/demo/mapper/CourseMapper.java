package com.trunghieu.demo.mapper;

import com.trunghieu.demo.dto.AddCourseDto;
import com.trunghieu.demo.dto.AddDepartmentDto;
import com.trunghieu.demo.dto.CourseDto;
import com.trunghieu.demo.entity.Course;
import com.trunghieu.demo.entity.Department;
import com.trunghieu.demo.entity.Instructor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseMapper {

    public static CourseDto mapToCourseDto(Course course) {

        if (course == null) throw new NullPointerException("Course is null");

        return new CourseDto(
                course.getCode(),
                course.getTitle(),
                course.getDescription(),
                course.getDepartment().getCode(),
                course.getInstructor().getId()
        );
    }

    public static Course mapToCourse(CourseDto courseDto,
                                     Department department,
                                     Instructor instructor,
                                     Course course) {

        if (courseDto == null ||
                department == null ||
                instructor == null ||
                course == null
        ) throw new NullPointerException("Parameters is null");

        course.setCode(courseDto.getCode());
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setDepartment(department);
        course.setInstructor(instructor);

        return course;
    }

    public static Course mapToCourseFromAddDto(AddCourseDto addCourseDto,
                                               Department department,
                                               Instructor instructor) {
        if (addCourseDto == null ||
        department == null ||
        instructor == null) throw new NullPointerException("Parameters is null");

        return new Course(
                addCourseDto.getTitle(),
                addCourseDto.getDescription(),
                department,
                instructor
        );
    }
}
