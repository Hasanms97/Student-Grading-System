package Repository;




import Model.StudentCourse;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface IStudentCourseDAO {
    public void insertStudentCourse(StudentCourse studentCourse) throws SQLException;

    public void deleteStudentCourse(int id) throws SQLException;

    public List<StudentCourse> getStudentCourse(int id) throws SQLException;
    public double getStudentMark(int studentId, int courseId) throws SQLException;
    public double getMedian(int courseId) throws SQLException;

    public List<StudentCourse> getAllStudentCourses() throws SQLException;
    public HashMap<String,Double> getCourseStatics(int id) throws SQLException;
}
