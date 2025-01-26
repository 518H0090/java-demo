package com.trunghieu.demo.repository;

import com.trunghieu.demo.entity.Course;
import com.trunghieu.demo.entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<Student, Integer> {
    @EntityGraph(attributePaths = {"courses"})
    @Query("SELECT s FROM Student s")
    List<Student> findAllStudentsWithCourse();
}
