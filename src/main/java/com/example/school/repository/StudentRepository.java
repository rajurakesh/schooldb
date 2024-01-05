package com.example.school.repository;

import java.util.*;
import com.example.school.model.Student;

public interface StudentRepository {
    ArrayList<Student> getStudents();

    Student addStudent(Student student);

    Student getStudentById(int studentId);

    void deleteStudent(int studentId);

    Student updateStudents(int studentId, Student student);
    int addBulkStudentsCount(ArrayList<Student> student);

}