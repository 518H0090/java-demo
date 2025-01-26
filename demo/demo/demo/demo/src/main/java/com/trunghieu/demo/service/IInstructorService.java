package com.trunghieu.demo.service;

import com.trunghieu.demo.dto.AddInstructorDto;
import com.trunghieu.demo.dto.InstructorDto;

import java.util.List;

public interface IInstructorService {
    InstructorDto addInstructor(AddInstructorDto studentDto);
    void updateInstructor(InstructorDto studentDto);
    InstructorDto getInstructorById(int id);
    List<InstructorDto> getInstructors();
    void deleteInstructor(int id);
}
