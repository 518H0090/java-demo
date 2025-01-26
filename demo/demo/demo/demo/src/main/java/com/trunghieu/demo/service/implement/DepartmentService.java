package com.trunghieu.demo.service.implement;

import com.trunghieu.demo.dto.AddDepartmentDto;
import com.trunghieu.demo.dto.DepartmentDto;
import com.trunghieu.demo.entity.Department;
import com.trunghieu.demo.entity.Student;
import com.trunghieu.demo.exception.NotFoundException;
import com.trunghieu.demo.mapper.DepartmentMapper;
import com.trunghieu.demo.mapper.StudentMapper;
import com.trunghieu.demo.repository.DepartmentRepository;
import com.trunghieu.demo.service.IDepartmentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DepartmentService implements IDepartmentService {

    @Autowired
    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto addDepartment(AddDepartmentDto addDepartmentDto) {
        Department department = DepartmentMapper.mapToDepartmentFromAddDto(addDepartmentDto);
        Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public void updateDepartment(DepartmentDto departmentDto) {
        var findDepartment = departmentRepository.findDepartmentsByCode(departmentDto.getCode())
                .orElseThrow(() -> new NotFoundException("Cannot find department with code: " + departmentDto.getCode()));

        DepartmentMapper.mapToDepartment(departmentDto, findDepartment);

        departmentRepository.save(findDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        var findDepartment = departmentRepository.findDepartmentsByCode(code).orElseThrow(() -> new NotFoundException("Cannot find department with code: " + code));
        return DepartmentMapper.mapToDepartmentDto(findDepartment);
    }

    @Override
    public List<DepartmentDto> getDepartments() {
        var allDepartments = departmentRepository.findAll();
        return allDepartments.stream().map(DepartmentMapper::mapToDepartmentDto).collect(Collectors.toList());
    }

    @Override
    public void deleteDepartment(String code) {
        var findDepartment = departmentRepository.findDepartmentsByCode(code).orElseThrow(() -> new NotFoundException("Cannot find department with code: " + code));
        departmentRepository.delete(findDepartment);
    }
}
