package com.trunghieu.demo.config;

import com.trunghieu.demo.entity.Course;
import com.trunghieu.demo.entity.Department;
import com.trunghieu.demo.entity.Instructor;
import com.trunghieu.demo.entity.Student;
import com.trunghieu.demo.repository.CourseRepository;
import com.trunghieu.demo.repository.DepartmentRepository;
import com.trunghieu.demo.repository.InstructorRepository;
import com.trunghieu.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class AutoConfig {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final InstructorRepository instructorRepository;

    @Bean
    public CommandLineRunner insertData(
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            DepartmentRepository departmentRepository,
            InstructorRepository instructorRepository
    ) {
        return args -> {
            // Create departments
            Department department1 = new Department("Computer Science");
            Department department2 = new Department("Mathematics");
            departmentRepository.saveAll(List.of(department1, department2));

            // Create instructors
            Instructor instructor1 = new Instructor("John Doe", "123 Main St", "123456789");
            Instructor instructor2 = new Instructor("Jane Smith", "456 Elm St", "987654321");
            instructorRepository.saveAll(List.of(instructor1, instructor2));

            // Create courses
            Course course1 = new Course("Java Basics", "Introduction to Java", department1, instructor1);
            Course course2 = new Course("Advanced Math", "Higher-level Mathematics", department2, instructor2);
            courseRepository.saveAll(List.of(course1, course2));

            // Create students
            Student student1 = new Student("Alice", "789 Pine St", "555123456");
            Student student2 = new Student("Bob", "101 Maple St", "555654321");
            student1.setCourses(List.of(course1, course2));
            student2.setCourses(List.of(course1));
            studentRepository.saveAll(List.of(student1, student2));
        };
    }
}
