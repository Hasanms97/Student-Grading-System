package com.example.elearning.Controller;

import com.example.elearning.Model.Student;
import com.example.elearning.Model.StudentCourse;
import com.example.elearning.Service.CourseService;
import com.example.elearning.Service.StudentCourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "CourseServlet", value = "/CourseServlet")
public class CourseServlet extends HttpServlet {
    CourseService courseService = new CourseService();
    StudentCourseService studentCourseService = new StudentCourseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI().toString();
        if (url.equals("/course-registration")) {
            try {
                CourseRegistrationHandler(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (url.equals("/course-reg")) {
            try {
                CourseRegistrationSubmitHandler(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (url.equals("/course-statics")) {
            try {
                RegisteredCourseHandler(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (url.equals("/course-marks")) {
            try {
                CourseMarksHandler(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void CourseRegistrationHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Student student = (Student) request.getSession().getAttribute("user");
        courseService.getAllCoursesNotRegistered(student.getStudentId());
        request.setAttribute("availableCourses", courseService.getAllCoursesNotRegistered(student.getStudentId()));
        request.getRequestDispatcher("CourseRegisteration.jsp").forward(request, response);
    }

    public void RegisteredCourseHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Student student = (Student) request.getSession().getAttribute("user");
        request.setAttribute("registeredCourses", studentCourseService.getStudentCourse(student.getStudentId()));
        request.getRequestDispatcher("registeredCourses.jsp").forward(request, response);
    }

    public void CourseRegistrationSubmitHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String courseId = request.getParameter("courseId");
        Student student = (Student) request.getSession().getAttribute("user");
        studentCourseService.insertStudentCourse(new StudentCourse(student.getStudentId(), Integer.parseInt(courseId), 0));
        CourseRegistrationHandler(request, response);
    }

    public void CourseMarksHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Student student = (Student) request.getSession().getAttribute("user");
        HashMap<String, Double> map = studentCourseService.getCourseStatics(courseId);
        map.put("score", studentCourseService.getStudentMark(student.getStudentId(), courseId));
        map.put("MEDIAN", studentCourseService.getMedian(courseId));
        request.setAttribute("courseName",courseService.getCourse(courseId).getName());
        request.setAttribute("statics", map);
        request.getRequestDispatcher("courseStatics.jsp").forward(request, response);
    }
}
