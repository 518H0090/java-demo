package com.trunghieu.demo.service.implement;

import com.trunghieu.demo.dto.AddStudentDto;
import com.trunghieu.demo.dto.StudentDto;
import com.trunghieu.demo.entity.Student;
import com.trunghieu.demo.exception.NotFoundException;
import com.trunghieu.demo.mapper.StudentMapper;
import com.trunghieu.demo.repository.StudentRepository;
import com.trunghieu.demo.service.IStudentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class StudentService implements IStudentService {

    @Autowired
    private final StudentRepository studentRepository;

    @Override
    public StudentDto addStudent(AddStudentDto studentDto) {
        Student student = StudentMapper.mapToStudentFromAddDto(studentDto);
        Student savedStudent = studentRepository.save(student);

        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public void updateStudent(StudentDto studentDto) {

        var findStudent = studentRepository.findById(studentDto.getId())
                .orElseThrow(() -> new NotFoundException("Cannot find student with id: " + studentDto.getId()));

        StudentMapper.mapToStudent(studentDto, findStudent);

        studentRepository.save(findStudent);
    }

    @Override
    public StudentDto getStudentById(int id) {
        var findStudent = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot find student with id: " + id));
        return StudentMapper.mapToStudentDto(findStudent);
    }

    @Override
    public List<StudentDto> getStudents() {
        var allStudents = studentRepository.findAll();
        return allStudents.stream().map(StudentMapper::mapToStudentDto).collect(Collectors.toList());
    }

    @Override
    public void deleteStudent(int id) {
        var findStudent = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot find student with id: " + id));
        studentRepository.delete(findStudent);
    }
}
