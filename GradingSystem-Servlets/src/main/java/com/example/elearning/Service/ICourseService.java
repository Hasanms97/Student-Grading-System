package com.example.elearning.Service;

import com.example.elearning.Model.Course;

import java.sql.SQLException;
import java.util.List;

public interface ICourseService {
    public void insertCourse(Course course) throws SQLException;

    public void deleteCourse(int id) throws SQLException;

    public Course getCourse(int id) throws SQLException;

    public List<Course> getAllCourses() throws SQLException;

    public List<Course> getAllCoursesNotRegistered(int id) throws SQLException;
}
