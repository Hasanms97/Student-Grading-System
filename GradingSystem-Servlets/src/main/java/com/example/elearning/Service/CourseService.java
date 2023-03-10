package com.example.elearning.Service;

import com.example.elearning.Model.Course;
import com.example.elearning.Repository.CourseDAO;
import com.example.elearning.Repository.ICourseDAO;

import java.sql.SQLException;
import java.util.List;

public class CourseService implements ICourseService{
    ICourseDAO courseDAO = new CourseDAO();
    @Override
    public void insertCourse(Course course) throws SQLException {
        courseDAO.insertCourse(course);
    }

    @Override
    public void deleteCourse(int id) throws SQLException {
        courseDAO.deleteCourse(id);
    }

    @Override
    public Course getCourse(int id) throws SQLException {
        return courseDAO.getCourse(id);
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        return courseDAO.getAllCourses();
    }

    @Override
    public List<Course> getAllCoursesNotRegistered(int id) throws SQLException {
        return courseDAO.getAllCoursesNotRegistered(id);
    }
}
