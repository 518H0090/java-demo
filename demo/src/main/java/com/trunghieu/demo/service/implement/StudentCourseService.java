package com.trunghieu.demo.service.implement;

import com.trunghieu.demo.dto.StudentCourseDto;
import com.trunghieu.demo.dto.StudentCourseIdDto;
import com.trunghieu.demo.dto.StudentIncludeCourseDto;
import com.trunghieu.demo.entity.Course;
import com.trunghieu.demo.entity.Student;
import com.trunghieu.demo.exception.BadRequestException;
import com.trunghieu.demo.exception.NotFoundException;
import com.trunghieu.demo.mapper.StudentCourseMapper;
import com.trunghieu.demo.repository.CourseRepository;
import com.trunghieu.demo.repository.StudentCourseRepository;
import com.trunghieu.demo.repository.StudentRepository;
import com.trunghieu.demo.service.IStudentCourseService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class StudentCourseService implements IStudentCourseService {

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final StudentCourseRepository studentCourseRepository;


    @Override
    public void addStudentCourse(StudentCourseIdDto request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student not found!"));

        Course course = courseRepository.findCourseByCode(request.getCourseCode())
                .orElseThrow(() -> new NotFoundException("Course not found!"));

        if (student.getCourses().contains(course)) {
            throw new BadRequestException("Student is enrolled in this course!");
        }

        student.getCourses().add(course);
    }

    @Override
    public void deleteStudentCourse(StudentCourseIdDto request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student not found!"));

        Course course = courseRepository.findCourseByCode(request.getCourseCode())
                .orElseThrow(() -> new NotFoundException("Course not found!"));

        if (!student.getCourses().contains(course)) {
            throw new BadRequestException("Student is not enrolled in this course!");
        }

        student.getCourses().remove(course);

        studentRepository.save(student);
    }

    @Override
    public StudentCourseDto getStudentCourse(StudentCourseIdDto request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student not found!"));

        Course course = courseRepository.findCourseByCode(request.getCourseCode())
                .orElseThrow(() -> new NotFoundException("Course not found!"));

        if (!student.getCourses().contains(course)) {
            throw new BadRequestException("Student is not enrolled in this course!");
        }

        return StudentCourseMapper.mapToStudentIncludeCourseDto(student, course);
    }

    @Override
    public List<StudentIncludeCourseDto> findAllStudentsWithCourse() {
        var studentsIncludeCourse = studentCourseRepository.findAllStudentsWithCourse();
        var studentsIncludeCourseDto =
                studentsIncludeCourse.stream().map(StudentCourseMapper::mapToStudentIncludeCourseDto)
                        .collect(Collectors.toList());;
        return studentsIncludeCourseDto;
    }
}
