package com.trunghieu.demo.service.implement;

import com.trunghieu.demo.dto.AddInstructorDto;
import com.trunghieu.demo.dto.InstructorDto;
import com.trunghieu.demo.entity.Instructor;
import com.trunghieu.demo.exception.NotFoundException;
import com.trunghieu.demo.mapper.InstructorMapper;
import com.trunghieu.demo.repository.InstructorRepository;
import com.trunghieu.demo.service.IInstructorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InstructorService implements IInstructorService {

    @Autowired
    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public InstructorDto addInstructor(AddInstructorDto addInstructorDto) {
        Instructor instructor = InstructorMapper.mapToInstructorFromAddDto(addInstructorDto);
        Instructor savedInstructor = instructorRepository.save(instructor);

        return InstructorMapper.mapToInstructorDto(savedInstructor);
    }

    @Override
    public void updateInstructor(InstructorDto instructorDto) {
        var findInstructor = instructorRepository.findById(instructorDto.getId())
                .orElseThrow(() -> new NotFoundException("Cannot find instructor with id: " + instructorDto.getId()));

        InstructorMapper.mapToInstructor(instructorDto, findInstructor);

        instructorRepository.save(findInstructor);
    }

    @Override
    public InstructorDto getInstructorById(int id) {
        var findInstructor = instructorRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot find instructor with id: " + id));
        return InstructorMapper.mapToInstructorDto(findInstructor);
    }

    @Override
    public List<InstructorDto> getInstructors() {
        var allInstructors = instructorRepository.findAll();
        return allInstructors.stream().map(InstructorMapper::mapToInstructorDto).collect(Collectors.toList());
    }

    @Override
    public void deleteInstructor(int id) {
        var findInstructor = instructorRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot find instructor with id: " + id));
        instructorRepository.delete(findInstructor);
    }
}
