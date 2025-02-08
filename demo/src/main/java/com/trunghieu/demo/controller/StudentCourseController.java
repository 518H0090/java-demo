package com.trunghieu.demo.controller;

import com.trunghieu.demo.dto.AddCourseDto;
import com.trunghieu.demo.dto.CourseDto;
import com.trunghieu.demo.dto.StudentCourseIdDto;
import com.trunghieu.demo.service.implement.StudentCourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/trunghieu/student-course")
public class StudentCourseController {

    @Autowired
    private final StudentCourseService studentCourseService;

    @GetMapping
    @RequestMapping("students-include-course")
    public ResponseEntity<?> GetStudentIncludeCourses() {
        var courses = studentCourseService.findAllStudentsWithCourse();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("find-student-course")
    public ResponseEntity<?> FindStudentCourse(@RequestBody @Valid StudentCourseIdDto request) {
        var course = studentCourseService.getStudentCourse(request);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("add-student-course")
    public ResponseEntity<?> AddStudentCourse(@RequestBody @Valid StudentCourseIdDto request) {
        studentCourseService.addStudentCourse(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    @RequestMapping("delete-student-course")
    public ResponseEntity<?> DeleteStudentCourse(@RequestBody @Valid StudentCourseIdDto request) {
        studentCourseService.deleteStudentCourse(request);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
