package com.trunghieu.demo.mapper;

import com.trunghieu.demo.dto.AddStudentDto;
import com.trunghieu.demo.dto.StudentDto;
import com.trunghieu.demo.entity.Course;
import com.trunghieu.demo.entity.Department;
import com.trunghieu.demo.entity.Instructor;
import com.trunghieu.demo.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentMapperTest {

    private Department department1;
    private Department department2;
    private Instructor instructor1;
    private Instructor instructor2;
    private Student student1;
    private Student student2;
    private Course course1;
    private Course course2;
    private StudentDto studentDto1;
    private StudentDto studentDto2;
    private AddStudentDto addStudentDto;
    private Student verifyAddStudentDto;

    @BeforeEach
    void setUp() {
        // Create departments
        department1 = new Department("Computer Science");
        department2 = new Department("Mathematics");

        // Create instructors
        instructor1 = new Instructor("John Doe", "123 Main St", "123456789");
        instructor2 = new Instructor("Jane Smith", "456 Elm St", "987654321");

        // Create courses
        course1 = new Course("Java Basics", "Introduction to Java", department1, instructor1);
        course2 = new Course("Advanced Math", "Higher-level Mathematics", department2, instructor2);

        // Create students
        student1 = new Student("Alice", "789 Pine St", "555123456");
        student2 = new Student("Bob", "101 Maple St", "555654321");
        student1.setCourses(List.of(course1, course2));
        student2.setCourses(List.of(course1));
        student1.setId(1);
        student2.setId(2);

        studentDto1 = StudentDto.build(1,"Alice", "789 Pine St", "555123456");
        studentDto2 = StudentDto.builder()
                .id(2)
                .name("Bob")
                .address("101 Maple St")
                .phone("555654321")
                .build();

        addStudentDto = AddStudentDto.build("Haruna", "456 Tokyo St", "986654321");
        verifyAddStudentDto = new Student("Haruna", "456 Tokyo St", "986654321");
    }

    @Test
    public void mapToStudentDto_AssertEquals() {
        var studentTestDto1 = StudentMapper.mapToStudentDto(student1);

        assertEquals(student1.getId(), studentTestDto1.getId());
        assertEquals(student1.getName(), studentTestDto1.getName());
        assertEquals(student1.getAddress(), studentTestDto1.getAddress());
        assertEquals(student1.getPhone(), studentTestDto1.getPhone());

        var studentTestDto2 = StudentMapper.mapToStudentDto(student2);

        assertEquals(student2.getId(), studentTestDto2.getId());
        assertEquals(student2.getName(), studentTestDto2.getName());
        assertEquals(student2.getAddress(), studentTestDto2.getAddress());
        assertEquals(student2.getPhone(), studentTestDto2.getPhone());
    }

    @Test
    public void mapToStudentDto_ThrowException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            StudentMapper.mapToStudentDto(null);
        });

       assertEquals( "student is null", exception.getMessage());
    }

    @Test
    public void mapToStudent() {
        var studentTest1 = StudentMapper.mapToStudent(studentDto1, student1);

        assertEquals(student1.getName(), studentTest1.getName());
        assertEquals(student1.getAddress(), studentTest1.getAddress());
        assertEquals(student1.getPhone(), studentTest1.getPhone());

        var studentTest2 = StudentMapper.mapToStudent(studentDto2, student2);

        assertEquals(student2.getName(), studentTest2.getName());
        assertEquals(student2.getAddress(), studentTest2.getAddress());
        assertEquals(student2.getPhone(), studentTest2.getPhone());
    }

    @Test
    public void mapToStudent_ThrowException() {
        NullPointerException exception1 = assertThrows(NullPointerException.class, () -> {
            StudentMapper.mapToStudent(null, student1);
        });

        assertEquals("studentDto is null", exception1.getMessage());

        NullPointerException exception2 = assertThrows(NullPointerException.class, () -> {
            StudentMapper.mapToStudent(studentDto1, null);
        });

        assertEquals("student is null", exception2.getMessage());
    }

    @Test
    public void mapToStudentFromAddDto() {
        var addStudentTestDto = StudentMapper.mapToStudentFromAddDto(addStudentDto);

        assertEquals(verifyAddStudentDto.getName(), addStudentTestDto.getName());
        assertEquals(verifyAddStudentDto.getAddress(), addStudentTestDto.getAddress());
        assertEquals(verifyAddStudentDto.getPhone(), addStudentTestDto.getPhone());
    }

    @Test
    public void mapToStudentFromAddDto_ThrowException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
           StudentMapper.mapToStudentFromAddDto(null);
        });

        assertEquals( "addStudentDto is null", exception.getMessage());
    }
}