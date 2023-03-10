package com.example.elearningspringboot.Controller;

import com.example.elearningspringboot.Entity.Student;
import com.example.elearningspringboot.Service.CourseService;
import com.example.elearningspringboot.Service.CourseStudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    CourseStudentService courseStudentService;

    @GetMapping("/registered-course")
    public String getRegisteredCourse(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("user");
        model.addAttribute("studentCourses", courseService.getAllStudentCoursesById(student.getStudent_id()));
        return "registeredCourses";
    }

    @PostMapping("/course-statics")
    public String getSignIn(@RequestParam("courseId") int courseId,Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("user");
        model.addAttribute("statics",courseStudentService.getCourseStatics(courseId,student.getStudent_id()));
        model.addAttribute("courseName",courseService.getCourse(courseId).getName());
        return "courseStatics";
    }
}
