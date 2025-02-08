package com.trunghieu.demo.service.implement;

import com.trunghieu.demo.dto.AddCourseDto;
import com.trunghieu.demo.dto.CourseDto;
import com.trunghieu.demo.entity.Course;
import com.trunghieu.demo.entity.Department;
import com.trunghieu.demo.entity.Student;
import com.trunghieu.demo.exception.NotFoundException;
import com.trunghieu.demo.mapper.CourseMapper;
import com.trunghieu.demo.mapper.DepartmentMapper;
import com.trunghieu.demo.repository.CourseRepository;
import com.trunghieu.demo.repository.DepartmentRepository;
import com.trunghieu.demo.repository.InstructorRepository;
import com.trunghieu.demo.service.ICourseService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CourseService implements ICourseService {

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final DepartmentRepository departmentRepository;

    @Autowired
    private final InstructorRepository instructorRepository;

    @Override
    public CourseDto addCourse(AddCourseDto courseDto) {
        var findDepartment = departmentRepository.findDepartmentsByCode(courseDto.getDepartmentCode()).
                orElseThrow(() -> new NotFoundException("Cannot find department with code: " + courseDto.getDepartmentCode()));
        var findInstructor = instructorRepository.findById(courseDto.getInstructorId()).
                orElseThrow(() -> new NotFoundException("Cannot find instructor with id: " + courseDto.getInstructorId()));

        Course course = CourseMapper.mapToCourseFromAddDto(courseDto, findDepartment, findInstructor);
        Course savedCourse = courseRepository.save(course);

        return CourseMapper.mapToCourseDto(savedCourse);
    }

    @Override
    public void updateCourse(CourseDto courseDto) {
        var findCourse = courseRepository.findCourseByCode(courseDto.getCode())
                .orElseThrow(() -> new NotFoundException("Cannot find course with code: " + courseDto.getCode()));
        var findDepartment = departmentRepository.findDepartmentsByCode(courseDto.getDepartmentCode()).
                orElseThrow(() -> new NotFoundException("Cannot find department with code: " + courseDto.getDepartmentCode()));
        var findInstructor = instructorRepository.findById(courseDto.getInstructorId()).
                orElseThrow(() -> new NotFoundException("Cannot find instructor with id: " + courseDto.getInstructorId()));

        CourseMapper.mapToCourse(courseDto, findDepartment, findInstructor, findCourse);

        courseRepository.save(findCourse);
    }

    @Override
    public CourseDto getCourseByCode(String code) {
        var findCourse = courseRepository.findCourseByCode(code)
                .orElseThrow(() -> new NotFoundException("Cannot find course with code: " + code));

        return CourseMapper.mapToCourseDto(findCourse);
    }

    @Override
    public List<CourseDto> getCourses() {
        var allCourses = courseRepository.findAll();
        return allCourses.stream().map(CourseMapper::mapToCourseDto).collect(Collectors.toList());
    }

    @Override
    public void deleteCourse(String code) {
        var findCourse = courseRepository.findCourseByCode(code).orElseThrow(() -> new NotFoundException("Cannot find department with code: " + code));
        courseRepository.delete(findCourse);
    }
}
