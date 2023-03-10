package com.example.elearning.Repository;


import com.example.elearning.Model.Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO implements ICourseDAO {

    private static final String INSERT_COURSE_SQL = "INSERT INTO COURSE" + "  (name) VALUES "
            + " (?)";
    private static final String DELETE_COURSE_SQL = "delete from COURSE where course_id = ?";
    private static final String SELECT_COURSE_BY_ID_SQL = "select * from COURSE where course_id =?";
    private static final String SELECT_ALL_COURSES_SQL = "select * from COURSE";
    private static final String SELECT_STUDENTCOURSE_NOT_REGISTERED_SQL = "SELECT name,Course.course_id from Course inner join Course_Student on Course.course_id != Course_Student.course_id where student_id=?;";

    @Override
    public void insertCourse(Course course) throws SQLException {
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(INSERT_COURSE_SQL);
            preparedStatement.setString(1, course.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void deleteCourse(int id) throws SQLException {
        try {
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(DELETE_COURSE_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public Course getCourse(int id) throws SQLException {
        Course course = null;
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_COURSE_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                course = new Course();
                course.setCourseId(resultSet.getInt("course_id"));
                course.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return course;
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_ALL_COURSES_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setCourseId(resultSet.getInt("course_id"));
                course.setName(resultSet.getString("name"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return courses;
    }

    @Override
    public List<Course> getAllCoursesNotRegistered(int id) throws SQLException {
        List<Course> courses = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_STUDENTCOURSE_NOT_REGISTERED_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setCourseId(resultSet.getInt("course_id"));
                course.setName(resultSet.getString("name"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return courses;
    }
}
