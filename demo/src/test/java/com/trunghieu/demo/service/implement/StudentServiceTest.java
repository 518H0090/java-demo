package com.trunghieu.demo.service.implement;

import com.trunghieu.demo.dto.AddStudentDto;
import com.trunghieu.demo.dto.StudentDto;
import com.trunghieu.demo.entity.Course;
import com.trunghieu.demo.entity.Department;
import com.trunghieu.demo.entity.Instructor;
import com.trunghieu.demo.entity.Student;
import com.trunghieu.demo.exception.NotFoundException;
import com.trunghieu.demo.mapper.StudentMapper;
import com.trunghieu.demo.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

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
    private Student studentConvertFromAddDto;
    private MockedStatic<StudentMapper> mockedStatic;
    private List<Student> students;
    private List<StudentDto> studentDtos;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockedStatic = mockStatic(StudentMapper.class);

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
        studentConvertFromAddDto = new Student("Haruna", "456 Tokyo St", "986654321");

        students = List.of(student1, student2);
        studentDtos = List.of(studentDto1, studentDto2);
    }

    @AfterEach
    void tearDown() {
        mockedStatic.close();
    }

    @Test
    void addStudent() {
        var savedStudent = new Student(1, "Haruna", "456 Tokyo St", "986654321");
        var savedStudentDto = StudentDto.build(1, "Haruna", "456 Tokyo St", "986654321");

        mockedStatic.when(() -> StudentMapper.mapToStudentFromAddDto(addStudentDto)).thenReturn(studentConvertFromAddDto);

        when(studentRepository.save(studentConvertFromAddDto)).thenReturn(savedStudent);

        when(StudentMapper.mapToStudentDto(savedStudent)).thenReturn(savedStudentDto);

        StudentDto resultStudentDto = studentService.addStudent(addStudentDto);

        assertEquals(savedStudentDto, resultStudentDto);

        verify(studentRepository, times(1)).save(studentConvertFromAddDto);
        mockedStatic.verify(times(1), () -> StudentMapper.mapToStudentFromAddDto(addStudentDto));
        mockedStatic.verify(times(1), () -> StudentMapper.mapToStudentDto(savedStudent));
    }

    @Test
    void addStudent_ThrowException() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> studentService.addStudent(null));

        assertEquals(exception.getMessage(), "studentDto is null");
    }

    @Test
    void updateStudent() {
        var student3 = new Student(3, "David", "164 Maple St", "781054321");
        var updateStudentDto = StudentDto.build(3, "Trung Hieu", "Binh Tan", "738954321");

        var updatedStudent3 = new Student(3, "Trung Hieu", "Binh Tan", "738954321");

        when(studentRepository.findById(updateStudentDto.getId())).thenReturn(Optional.of(student3));
        mockedStatic.when(() -> StudentMapper.mapToStudent(updateStudentDto, student3)).thenReturn(updatedStudent3);
        when(studentRepository.save(updatedStudent3)).thenReturn(updatedStudent3);

        studentService.updateStudent(updateStudentDto);

        verify(studentRepository, times(1)).findById(updateStudentDto.getId());
        verify(studentRepository, times(1)).save(updatedStudent3);
    }

    @Test
    void updateStudent_ThrowException() {

        var updateStudentDto = StudentDto.build(3, "Trung Hieu", "Binh Tan", "738954321");

        when(studentRepository.findById(updateStudentDto.getId())).thenThrow(
                new NotFoundException("Cannot find student with id: " + updateStudentDto.getId())
        );

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> studentService.updateStudent(updateStudentDto));

        assertEquals(exception.getMessage(), "Cannot find student with id: " + updateStudentDto.getId());
    }

    @Test
    void getStudentById() {
        when(studentRepository.findById(2)).thenReturn(Optional.of(student2));
        mockedStatic.when(()-> StudentMapper.mapToStudentDto(student2)).thenReturn(studentDto2);

        assertEquals(student2.getId(), studentDto2.getId());
        assertEquals(student2.getName(), studentDto2.getName());
        assertEquals(student2.getAddress(), studentDto2.getAddress());
        assertEquals(student2.getPhone(), studentDto2.getPhone());
    }

    @Test
    void getStudentById_ThrowException() {

        when(studentRepository.findById(2)).thenThrow(new NotFoundException("Cannot find student with id: " + 2));

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> studentService.getStudentById(2));

        assertEquals(exception.getMessage(), "Cannot find student with id: " + 2);
    }

    @Test
    void getStudents() {
        // Mock the findAll call to return the students list
        when(studentRepository.findAll()).thenReturn(students);

        // Mock the mapToStudentDto static method to return the studentDtos list
        mockedStatic.when(() -> StudentMapper.mapToStudentDto(any(Student.class)))
                .thenReturn(studentDtos.get(0))
                .thenReturn(studentDtos.get(1));

        // When
        List<StudentDto> result = studentService.getStudents();

        // Then
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Bob", result.get(1).getName());

        // Verify
        verify(studentRepository, times(1)).findAll();
        mockedStatic.verify(times(2), () -> StudentMapper.mapToStudentDto(any(Student.class)));
    }

    @Test
    void deleteStudent() {
        when(studentRepository.findById(student2.getId())).thenReturn(Optional.of(student2));
        doNothing().when(studentRepository).delete(student2);

        studentService.deleteStudent(student2.getId());

        verify(studentRepository, times(1)).findById(student2.getId());

        verify(studentRepository, times(1)).delete(student2);
    }

    @Test
    void deleteStudent_ThrowException() {
        when(studentRepository.findById(student2.getId()))
                .thenThrow(new NotFoundException("Cannot find student with id: " + student2.getId()));

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> studentService.deleteStudent(student2.getId()));

        assertEquals(exception.getMessage(), "Cannot find student with id: " + student2.getId());
    }
}