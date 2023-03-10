package com.example.elearningspringboot.Service;

import com.example.elearningspringboot.Entity.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getAllStudentCoursesById(int id);
    public Course getCourse(int id);
}
