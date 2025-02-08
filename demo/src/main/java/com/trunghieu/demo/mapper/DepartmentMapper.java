package com.trunghieu.demo.mapper;

import com.trunghieu.demo.dto.AddDepartmentDto;
import com.trunghieu.demo.dto.AddInstructorDto;
import com.trunghieu.demo.dto.DepartmentDto;
import com.trunghieu.demo.dto.InstructorDto;
import com.trunghieu.demo.entity.Department;
import com.trunghieu.demo.entity.Instructor;

public class DepartmentMapper  {
    public static DepartmentDto mapToDepartmentDto(Department department) {
        return new DepartmentDto(
                department.getCode(),
                department.getName()
        );
    }

    public static Department mapToDepartment(DepartmentDto departmentDto, Department department) {
        department.setCode(departmentDto.getCode());
        department.setName(departmentDto.getName());

        return department;
    }

    public static Department mapToDepartmentFromAddDto(AddDepartmentDto addDeparmentDto) {
        return new Department(
                addDeparmentDto.getName()
        );
    }
}
