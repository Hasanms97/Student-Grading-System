package com.example.elearning.Repository;


import com.example.elearning.Model.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDAO {
    public void insertStudent(Student student) throws SQLException;

    public void deleteStudent(int id) throws SQLException;

    public Student getStudent(int id) throws SQLException;

    public List<Student> getAllStudents() throws SQLException;
}
