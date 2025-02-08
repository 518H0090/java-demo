package com.trunghieu.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 255, nullable = false, unique = true)
    private String address;

    @Column(length = 16, nullable = false, unique = true)
    private String phone;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

    public Instructor(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
