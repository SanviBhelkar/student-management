package com.example.student_management.controller;


import com.example.student_management.model.Student;
import com.example.student_management.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody Student student) {
        try {
            studentService.registerStudent(student);
            return ResponseEntity.ok("Student registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentInfo(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudentInfo(@PathVariable Long id, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudentInfo(id, student));
    }
}