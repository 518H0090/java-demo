package com.trunghieu.demo.mapper;

import com.trunghieu.demo.dto.AddStudentDto;
import com.trunghieu.demo.dto.StudentDto;
import com.trunghieu.demo.entity.Student;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student student) {

        if (student == null) throw new NullPointerException("student is null");

        return StudentDto.build(
                student.getId(),
                student.getName(),
                student.getAddress(),
                student.getPhone()
        );
    }

    public static Student mapToStudent(StudentDto studentDto, Student student) {

        if (studentDto == null) throw new NullPointerException("studentDto is null");
        if (student == null) throw new NullPointerException("student is null");

        student.setName(studentDto.getName());
        student.setAddress(studentDto.getAddress());
        student.setPhone(studentDto.getPhone());

        return student;
    }

    public static Student mapToStudentFromAddDto(AddStudentDto addStudentDto) {

        if (addStudentDto == null) throw new NullPointerException("addStudentDto is null");

        return new Student(
                addStudentDto.getName(),
                addStudentDto.getAddress(),
                addStudentDto.getPhone()
        );
    }
}
