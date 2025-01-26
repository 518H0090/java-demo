package com.trunghieu.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String code;

    @Column(length = 50)
    private String title;

    @Column(length = 50)
    private String description;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "departmentCode", referencedColumnName = "code")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "instructorId")
    private Instructor instructor;

    public Course(String title, String description, Department department, Instructor instructor) {
        this.title = title;
        this.description = description;
        this.department = department;
        this.instructor = instructor;
    }
}
