package com.trunghieu.demo.service;

import com.trunghieu.demo.dto.AddStudentDto;
import com.trunghieu.demo.dto.StudentDto;

import java.util.List;

public interface IStudentService {
    StudentDto addStudent(AddStudentDto studentDto);
    void updateStudent(StudentDto studentDto);
    StudentDto getStudentById(int id);
    List<StudentDto> getStudents();
    void deleteStudent(int id);
}
