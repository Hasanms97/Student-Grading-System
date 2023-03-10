package com.example.elearningspringboot.Service;

import com.example.elearningspringboot.Entity.Student;

import java.util.List;

public interface StudentService {
    Student getStudent(int id);
    Student saveStudent(Student student);
    void deleteStudent(int id);
    List<Student> getStudents();
}
