package com.trunghieu.demo.mapper;

import com.trunghieu.demo.dto.AddInstructorDto;
import com.trunghieu.demo.dto.InstructorDto;
import com.trunghieu.demo.entity.Instructor;

public class InstructorMapper {

    public static InstructorDto mapToInstructorDto(Instructor instructor) {
        return new InstructorDto(
                instructor.getId(),
                instructor.getName(),
                instructor.getAddress(),
                instructor.getPhone()
        );
    }

    public static Instructor mapToInstructor(InstructorDto instructorDto, Instructor instructor) {
        instructor.setName(instructorDto.getName());
        instructor.setAddress(instructorDto.getAddress());
        instructor.setPhone(instructorDto.getPhone());

        return instructor;
    }

    public static Instructor mapToInstructorFromAddDto(AddInstructorDto addInstructorDto) {
        return new Instructor(
                addInstructorDto.getName(),
                addInstructorDto.getAddress(),
                addInstructorDto.getPhone()
        );
    }
}
