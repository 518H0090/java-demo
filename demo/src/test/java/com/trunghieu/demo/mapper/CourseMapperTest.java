package com.trunghieu.demo.mapper;

import com.trunghieu.demo.dto.AddCourseDto;
import com.trunghieu.demo.dto.AddStudentDto;
import com.trunghieu.demo.dto.CourseDto;
import com.trunghieu.demo.dto.StudentDto;
import com.trunghieu.demo.entity.Course;
import com.trunghieu.demo.entity.Department;
import com.trunghieu.demo.entity.Instructor;
import com.trunghieu.demo.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseMapperTest {

    private Department department1;
    private Department department2;
    private Instructor instructor1;
    private Instructor instructor2;
    private Student student1;
    private Student student2;
    private Course course1;
    private Course course2;
    private CourseDto courseDto1;
    private CourseDto courseDto2;
    private AddCourseDto addCourseDto;
    private Course expectedCourseDtoFromAdd;

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

        courseDto1 = new CourseDto(course1.getCode(),
                "Java Basics",
                "Introduction to Java",
                department1.getCode(),
                instructor1.getId());

        courseDto2 = new CourseDto(
                course2.getCode(),
                "Advanced Math",
                "Higher-level Mathematics",
                department2.getCode(), instructor2.getId());

        addCourseDto = new AddCourseDto(
                "This is a test",
                "Description Value",
                department1.getCode(),
                instructor1.getId()
        );

        expectedCourseDtoFromAdd = new Course(
                addCourseDto.getTitle(),
                addCourseDto.getDescription(),
                department1,
                instructor1
        );
    }

    @Test
    public void mapToCourseDto() {
        var courseTestDto1 = CourseMapper.mapToCourseDto(course1);

        assertEquals(courseDto1.getCode(), courseTestDto1.getCode());
        assertTrue(courseTestDto1.getTitle().equals(courseDto1.getTitle()));
        assertEquals(courseDto1.getDescription(), courseTestDto1.getDescription());
        assertEquals(courseDto1.getDepartmentCode(), courseTestDto1.getDepartmentCode());
        assertEquals(courseDto1.getInstructorId(), courseTestDto1.getInstructorId());

        var courseTestDto2 = CourseMapper.mapToCourseDto(course2);

        assertEquals(courseDto2.getCode(), courseTestDto2.getCode());
        assertTrue(courseTestDto2.getTitle().equals(courseDto2.getTitle()));
        assertEquals(courseDto2.getDescription(), courseTestDto2.getDescription());
        assertEquals(courseDto2.getDepartmentCode(), courseTestDto2.getDepartmentCode());
        assertEquals(courseDto2.getInstructorId(), courseTestDto2.getInstructorId());
    }

    @Test
    public void mapToCourseDto_ThrowException() {
       NullPointerException exception = assertThrows(NullPointerException.class, () -> CourseMapper.mapToCourseDto(null));
       assertEquals("Course is null", exception.getMessage());
    }

    @Test
    public void mapToCourse() {
        var courseTest1 = CourseMapper.mapToCourse(courseDto1, department1, instructor1, course1);

        assertEquals(courseDto1.getCode(), courseTest1.getCode());
        assertEquals(courseDto1.getTitle(), courseTest1.getTitle());
        assertEquals(courseDto1.getDescription(), courseTest1.getDescription());
        assertEquals(department1, courseTest1.getDepartment());
        assertEquals(instructor1, courseTest1.getInstructor());

        var courseTest2 = CourseMapper.mapToCourse(courseDto2, department2, instructor2, course2);

        assertEquals(courseDto2.getCode(), courseTest2.getCode());
        assertEquals(courseDto2.getTitle(), courseTest2.getTitle());
        assertEquals(courseDto2.getDescription(), courseTest2.getDescription());
        assertEquals(department2, courseTest2.getDepartment());
        assertEquals(instructor2, courseTest2.getInstructor());
    }

    @Test
    public void mapToCourse_ThrowException() {
        NullPointerException exception = assertThrows(NullPointerException.class, ()-> {
           CourseMapper.mapToCourse(courseDto2, null, null, course2);
        });

        assertEquals("Parameters is null", exception.getMessage());
    }

    @Test
    public void mapToCourseFromAddDto() {
        var courseTest1 = CourseMapper.mapToCourseFromAddDto(addCourseDto, department1, instructor1);

        assertEquals(expectedCourseDtoFromAdd.getTitle(), courseTest1.getTitle());
        assertEquals(expectedCourseDtoFromAdd.getDescription(), courseTest1.getDescription());
        assertEquals(expectedCourseDtoFromAdd.getDepartment(), courseTest1.getDepartment());
        assertEquals(expectedCourseDtoFromAdd.getInstructor(), courseTest1.getInstructor());
    }

    @Test
    public void mapToCourseFromAddDto_ThrowException() {
       NullPointerException exception = assertThrows(NullPointerException.class, ()-> {
          CourseMapper.mapToCourseFromAddDto(addCourseDto, null, null);
       });

       assertEquals("Parameters is null", exception.getMessage());
    }
}