package com.example.school.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.example.school.model.Student;
import com.example.school.service.StudentH2Service;

@RestController
public class StudentController {

    @Autowired
    public StudentH2Service studentService;

    @GetMapping("/students")
    public ArrayList<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudents(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        return studentService.updateStudents(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        studentService.deleteStudent(studentId);
    }
    @PostMapping("/students/{bulk}")
    public String addBulkStudentsCount(@RequestBody ArrayList<Student> student) {
        int resultSize = addBulkStudentsCount(student);
        return "Successfully added" + resultSize + "students";
    }


}
