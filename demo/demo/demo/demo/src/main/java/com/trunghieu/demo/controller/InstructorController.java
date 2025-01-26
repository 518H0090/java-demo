package com.trunghieu.demo.controller;

import com.trunghieu.demo.dto.AddInstructorDto;
import com.trunghieu.demo.dto.InstructorDto;
import com.trunghieu.demo.service.implement.InstructorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/trunghieu/instructor")
public class InstructorController {

    @Autowired
    private final InstructorService instructorService;

    @GetMapping
    @RequestMapping("instructors")
    public ResponseEntity<?> GetInstructors() {
        var students = instructorService.getInstructors();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("instructor-by-id/{id}")
    public ResponseEntity<?> GetInstructorById(@PathVariable("id") int id) {
        var student = instructorService.getInstructorById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("add-instructor")
    public ResponseEntity<?> AddInstructor(@RequestBody @Valid AddInstructorDto request) {
        var addedInstructor = instructorService.addInstructor(request);
        return new ResponseEntity<>(addedInstructor, HttpStatus.CREATED);
    }

    @PutMapping
    @RequestMapping("update-instructor")
    public ResponseEntity<?> UpdateInstructor(@RequestBody @Valid InstructorDto request) {
        instructorService.updateInstructor(request);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    @RequestMapping("delete-instructor/{id}")
    public ResponseEntity<?> DeleteInstructor(@PathVariable("id") Integer instructorId) {
        instructorService.deleteInstructor(instructorId);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
