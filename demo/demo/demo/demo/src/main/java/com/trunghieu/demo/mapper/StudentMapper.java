package com.trunghieu.demo.mapper;

import com.trunghieu.demo.dto.AddStudentDto;
import com.trunghieu.demo.dto.StudentDto;
import com.trunghieu.demo.entity.Student;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student student) {

        return StudentDto.build(
                student.getId(),
                student.getName(),
                student.getAddress(),
                student.getPhone()
        );
    }

    public static Student mapToStudent(StudentDto studentDto, Student student) {

        student.setName(studentDto.getName());
        student.setAddress(studentDto.getAddress());
        student.setPhone(studentDto.getPhone());

        return student;
    }

    public static Student mapToStudentFromAddDto(AddStudentDto addStudentDto) {
        return new Student(
                addStudentDto.getName(),
                addStudentDto.getAddress(),
                addStudentDto.getPhone()
        );
    }
}
