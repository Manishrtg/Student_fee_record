package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // changes yaha se suru kiye


    @PostMapping("/{id}/deposit")
   // public ResponseEntity<Student>
    public ResponseEntity<Student> depositPartialFee(@PathVariable Long id, @RequestParam double amount) {
        Optional<Student> studentFeeOpt = studentService.getStudentById(id);
        if (studentFeeOpt.isPresent()) {
            Student studentFee = studentFeeOpt.get();
            try {
                studentFee.depositPartialFee((long) amount);
                studentService.saveStudent(studentFee);
                return ResponseEntity.ok(studentFee);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    // changes yaha pr  khatam kiye

    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
   }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        if (!studentService.getStudentById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        student.setId(id);
        return ResponseEntity.ok(studentService.saveStudent(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (!studentService.getStudentById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
