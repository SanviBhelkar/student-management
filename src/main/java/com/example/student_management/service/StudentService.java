package com.example.student_management.service;

import com.example.student_management.exception.EmailAlreadyExistsException;
import com.example.student_management.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void registerStudent(Student student) throws Exception {
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new Exception("Email already in use");
        }
        studentRepository.save(student);
    }

    public Student getStudentInfo(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public Student updateStudentInfo(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setCourse(student.getCourse());
        return studentRepository.save(existingStudent);
    }
}
