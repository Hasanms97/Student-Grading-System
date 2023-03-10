package com.example.elearning.Repository;


import com.example.elearning.Model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {

    private static final String INSERT_STUDENT_SQL = "INSERT INTO STUDENT" + "  (first_name,last_name) VALUES "
            + " (?,?)";
    private static final String SELECT_STUDENT_BY_ID_SQL = "select * from STUDENT where student_id =?";
    private static final String SELECT_ALL_STUDENTS_SQL = "select * from STUDENT";
    private static final String DELETE_STUDENT_SQL = "delete from STUDENT where student_id = ?";

    @Override
    public void insertStudent(Student student) {
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(INSERT_STUDENT_SQL);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void deleteStudent(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(DELETE_STUDENT_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public Student getStudent(int id) {
        Student student = null;
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_STUDENT_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                student=new Student();
                student.setStudentId(resultSet.getInt("student_id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_ALL_STUDENTS_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("student_id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));

                students.add(student);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return students;
    }
}
