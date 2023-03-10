package com.example.elearning.Service;

import com.example.elearning.Model.Student;
import com.example.elearning.Repository.IStudentDAO;
import com.example.elearning.Repository.StudentDAO;

import java.sql.SQLException;
import java.util.List;

public class StudentService implements IStudentService {

    IStudentDAO studentDAO = new StudentDAO();

    @Override
    public void insertStudent(Student student) throws SQLException {
        studentDAO.insertStudent(student);
    }

    @Override
    public void deleteStudent(int id) throws SQLException {
        studentDAO.deleteStudent(id);
    }

    @Override
    public Student getStudent(int id) throws SQLException {
        return studentDAO.getStudent(id);
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }
}
