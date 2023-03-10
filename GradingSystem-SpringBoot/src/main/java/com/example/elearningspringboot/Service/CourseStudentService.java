package com.example.elearningspringboot.Service;

import com.example.elearningspringboot.Entity.Course;
import com.example.elearningspringboot.Entity.Course_Student;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface CourseStudentService {
    List<Course_Student> getStudentCoursesById(int id);

    public double getStudentMark(int studentId, int courseId);

    int getMedian(int courseId);

    double getAverageCourseScore(int courseId);
    double getMinimumCourseScore(int courseId);
    double getMaximumCourseScore(int courseId);
    HashMap<String, Double> getCourseStatics(int courseId,int studentId);

}
