package com.trunghieu.demo.controller;

import com.trunghieu.demo.dto.AddDepartmentDto;
import com.trunghieu.demo.dto.AddInstructorDto;
import com.trunghieu.demo.dto.DepartmentDto;
import com.trunghieu.demo.dto.InstructorDto;
import com.trunghieu.demo.service.IDepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/trunghieu/department")
public class DepartmentController {

    @Autowired
    private final IDepartmentService departmentService;

    @GetMapping
    @RequestMapping("departments")
    public ResponseEntity<?> GetDepartments() {
        var departments = departmentService.getDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("department-by-code/{code}")
    public ResponseEntity<?> GetDepartmentByCode(@PathVariable("code") String code) {
        var department = departmentService.getDepartmentByCode(code);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("add-department")
    public ResponseEntity<?> AddDepartment(@RequestBody @Valid AddDepartmentDto request) {
        var addedDepartment = departmentService.addDepartment(request);
        return new ResponseEntity<>(addedDepartment, HttpStatus.CREATED);
    }

    @PutMapping
    @RequestMapping("update-department")
    public ResponseEntity<?> UpdateDepartment(@RequestBody @Valid DepartmentDto request) {
        departmentService.updateDepartment(request);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    @RequestMapping("delete-department/{code}")
    public ResponseEntity<?> DeleteInstructor(@PathVariable("code") String code) {
        departmentService.deleteDepartment(code);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
