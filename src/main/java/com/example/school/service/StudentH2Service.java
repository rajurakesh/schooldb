package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import javax.validation.OverridesAttribute;

import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;

@Service
public class StudentH2Service implements StudentRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Student> getStudents() {
        List<Student> studentsList = db.query("select * from STUDENT", new StudentRowMapper());
        ArrayList<Student> students = new ArrayList<>(studentsList);
        return students;
    }

    @Override
    public Student addStudent(Student student) {
        db.update("insert into STUDENT(studentName,gender,standard) values(?, ?, ?)", student.getStudentName(),
                student.getGender(), student.getStandard());
        Student studentsadd = db.queryForObject(
                "select * from STUDENT where studentName = ? and gender = ? and standard = ?",
                new StudentRowMapper(), student.getStudentName(), student.getGender(), student.getStandard());
        return studentsadd;
    }

    @Override
    public Student getStudentById(int studentId) {
        try {
            return db.queryForObject("select * from STUDENT where studentId = ?", new StudentRowMapper(), studentId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student updateStudents(int studentId, Student student) {

        if (student.getStudentName() != null) {
            db.update("update STUDENT set studentName ? where studentId = ?", student.getStudentName(), studentId);
        }
        if (student.getGender() != null) {
            db.update("update STUDENT set gender = ? where studentId = ?", student.getGender(), studentId);
        }
        if (student.getStandard() != 0) {
            db.update("update STUDENT set standard = ? where studentId = ?", student.getStandard(), studentId);
        }
        return getStudentById(studentId);
    }

    @Override
    public void deleteStudent(int studentId) {
        db.update("delete from STUDENT where studentId = ?", studentId);
    }

    @Override
    public int addBulkStudentsCount(ArrayList<Student> student) {
        for (Student each : student) {
            db.update("insert into STUDENT(studentName,gender,standard) values(?, ?, ?)", each.getStudentName(),
                    each.getGender(), each.getStandard());
        }
        return student.size();
    }


}