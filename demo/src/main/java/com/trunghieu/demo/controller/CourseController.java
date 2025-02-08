package com.trunghieu.demo.controller;

import com.trunghieu.demo.dto.AddCourseDto;
import com.trunghieu.demo.dto.CourseDto;
import com.trunghieu.demo.service.ICourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/trunghieu/course")
public class CourseController {

    @Autowired
    private final ICourseService courseService;

    @GetMapping
    @RequestMapping("courses")
    public ResponseEntity<?> GetCourses() {
        var courses = courseService.getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("course-by-code/{code}")
    public ResponseEntity<?> GetCourseByCode(@PathVariable("code") String code) {
        var course = courseService.getCourseByCode(code);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("add-course")
    public ResponseEntity<?> AddCourse(@RequestBody @Valid AddCourseDto request) {
        var addedCourse = courseService.addCourse(request);
        return new ResponseEntity<>(addedCourse, HttpStatus.CREATED);
    }

    @PutMapping
    @RequestMapping("update-course")
    public ResponseEntity<?> UpdateCourse(@RequestBody @Valid CourseDto request) {
        courseService.updateCourse(request);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    @RequestMapping("delete-course/{code}")
    public ResponseEntity<?> DeleteCourse(@PathVariable("code") String code) {
        courseService.deleteCourse(code);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
