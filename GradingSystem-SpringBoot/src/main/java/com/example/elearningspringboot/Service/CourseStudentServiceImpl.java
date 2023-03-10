package com.example.elearningspringboot.Service;

import com.example.elearningspringboot.Entity.Course_Student;
import com.example.elearningspringboot.Repository.CourseStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class CourseStudentServiceImpl implements CourseStudentService {

    @Autowired
    CourseStudentRepository courseStudentRepository;

    @Override
    public List<Course_Student> getStudentCoursesById(int id) {
        return courseStudentRepository.getStudentCoursesById(id);
    }

    @Override
    public double getStudentMark(int studentId, int courseId) {
        return courseStudentRepository.getStudentMark(studentId, courseId);
    }

    @Override
    public int getMedian(int courseId) {
        return courseStudentRepository.getMedian(courseId);
    }

    @Override
    public double getAverageCourseScore(int courseId) {
        return courseStudentRepository.getAverageCourseScore(courseId);
    }

    @Override
    public double getMinimumCourseScore(int courseId) {
        return courseStudentRepository.getMinimumCourseScore(courseId);
    }

    @Override
    public double getMaximumCourseScore(int courseId) {
        return courseStudentRepository.getMaximumCourseScore(courseId);
    }

    @Override
    public HashMap<String, Double> getCourseStatics(int courseId,int studentId) {
        HashMap<String,Double>map = new HashMap<>();
        double d  =  getAverageCourseScore(courseId);
        map.put("AVG", getAverageCourseScore(courseId));
        map.put("MIN",getMinimumCourseScore(courseId));
        map.put("MAX",getMaximumCourseScore(courseId));
        map.put("MED", (double) getMedian(courseId));
        map.put("SCOR",getStudentMark(studentId,courseId));
        return map;
    }

}
