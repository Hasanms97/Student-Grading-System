package com.example.elearningspringboot.Service;

import com.example.elearningspringboot.Entity.Course;
import com.example.elearningspringboot.Entity.Course_Student;
import com.example.elearningspringboot.Repository.CourseRepository;
import com.example.elearningspringboot.Repository.CourseStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseStudentRepository courseStudentRepository;

    @Override
    public List<Course>getAllStudentCoursesById(int id)
    {
        List<Course_Student> courseStudentList = courseStudentRepository.getStudentCoursesById(id);
        List<Course>courses = new ArrayList<>();
        for(int x = 0; x < courseStudentList.size() ;x++)
        {
            courses.add(courseRepository.getById(courseStudentList.get(x).getCourse_id()));
        }
        return courses;
    }

    @Override
    public Course getCourse(int id) {
        return courseRepository.getById(id);
    }
}