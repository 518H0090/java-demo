package com.trunghieu.demo.controller;

import com.trunghieu.demo.dto.AddStudentDto;
import com.trunghieu.demo.dto.StudentDto;
import com.trunghieu.demo.service.IStudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/trunghieu/student")
public class StudentController {

    @Autowired
    private final IStudentService studentService;

    @GetMapping
    @RequestMapping("students")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<?> GetStudents() {
        var students = studentService.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("student-by-id/{id}")
    public ResponseEntity<?> GetStudentById(@PathVariable("id") int studentId) {
        var students = studentService.getStudentById(studentId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("add-student")
    public ResponseEntity<?> AddStudent(@RequestBody @Valid AddStudentDto request) {
        var addedStudent = studentService.addStudent(request);
        return new ResponseEntity<>(addedStudent, HttpStatus.CREATED);
    }

    @PutMapping
    @RequestMapping("update-student")
    public ResponseEntity<?> UpdateStudent(@RequestBody @Valid StudentDto request) {
        studentService.updateStudent(request);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    @RequestMapping("delete-student/{id}")
    public ResponseEntity<?> DeleteStudent(@PathVariable("id") Integer studentId) {
        studentService.deleteStudent(studentId);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
