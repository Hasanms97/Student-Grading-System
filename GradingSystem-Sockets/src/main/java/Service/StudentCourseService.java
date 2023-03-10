package Service;



import Model.StudentCourse;
import Repository.IStudentCourseDAO;
import Repository.StudentCourseDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class StudentCourseService implements IStudentCourseService {

    IStudentCourseDAO studentCourseDAO = new StudentCourseDAO();

    @Override
    public void insertStudentCourse(StudentCourse studentCourse) throws SQLException {
        studentCourseDAO.insertStudentCourse(studentCourse);
    }

    @Override
    public void deleteStudentCourse(int id) throws SQLException {
        studentCourseDAO.deleteStudentCourse(id);
    }

    @Override
    public List<StudentCourse> getStudentCourse(int id) throws SQLException {
        return studentCourseDAO.getStudentCourse(id);
    }

    @Override
    public double getMedian( int courseId) throws SQLException {
        return studentCourseDAO.getMedian(courseId);
    }

    @Override
    public double getStudentMark(int studentId, int courseId) throws SQLException {
        return studentCourseDAO.getStudentMark(studentId,courseId);
    }

    @Override
    public List<StudentCourse> getAllStudentCourses() throws SQLException {
        return studentCourseDAO.getAllStudentCourses();
    }

    @Override
    public HashMap<String, Double> getCourseStatics(int id) throws SQLException {
        return studentCourseDAO.getCourseStatics(id);
    }
}
