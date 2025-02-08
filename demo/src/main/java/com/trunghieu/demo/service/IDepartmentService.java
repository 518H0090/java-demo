package com.trunghieu.demo.service;

import com.trunghieu.demo.dto.AddDepartmentDto;
import com.trunghieu.demo.dto.AddStudentDto;
import com.trunghieu.demo.dto.DepartmentDto;
import com.trunghieu.demo.dto.StudentDto;

import java.util.List;

public interface IDepartmentService {
    DepartmentDto addDepartment(AddDepartmentDto addDepartmentDto);
    void updateDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String code);
    List<DepartmentDto> getDepartments();
    void deleteDepartment(String code);
}
