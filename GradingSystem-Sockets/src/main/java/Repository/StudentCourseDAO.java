package Repository;



import Model.StudentCourse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentCourseDAO implements IStudentCourseDAO{

    private static final String INSERT_Course_Student_SQL = "INSERT INTO Course_Student" + "  (course_id,student_id,score) VALUES "
            + " (?,?,?)";
    private static final String DELETE_Course_Student_SQL = "delete from Course_Student where student_id = ?";
    private static final String SELECT_Course_Student_BY_STUDENT_ID_SQL = "select * from Course_Student where student_id =?";
    private static final String SELECT_Course_Mark_BY_SQL = "select score from Course_Student where student_id =? AND course_id =?";
    private static final String SELECT_ALL_Course_StudentS_SQL = "select * from Course_Student";
    private static final String SELECT_COURSE_MEDIAN_BY_ID_SQL = "SELECT AVG(dd.score) as median_val\n" +
            "FROM (\n" +
            "         SELECT d.score, @rownum:=@rownum+1 as `row_number`, @total_rows:=@rownum\n" +
            "         FROM Course_Student d, (SELECT @rownum:=0) r\n" +
            "         WHERE d.course_id=?\n" +
            "         ORDER BY d.score\n" +
            "     ) as dd\n" +
            "WHERE dd.row_number IN ( FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2) );";

    private static final String SELECT_Course_STATICS_BY_ID_SQL = "SELECT MAX(score), MIN(score),AVG(score)\n" +
            "FROM Course_Student where course_id=?;";
    
    @Override
    public void insertStudentCourse(StudentCourse studentCourse) {
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(INSERT_Course_Student_SQL);
            preparedStatement.setInt(1, studentCourse.getCourseId());
            preparedStatement.setInt(2, studentCourse.getStudentId());
            preparedStatement.setDouble(3, studentCourse.getMark());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void deleteStudentCourse(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(DELETE_Course_Student_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public List<StudentCourse> getStudentCourse(int id) {
        List<StudentCourse> studentCourses = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_Course_Student_BY_STUDENT_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setStudentId(resultSet.getInt("student_id"));
                studentCourse.setCourseId(resultSet.getInt("course_id"));
                studentCourse.setMark(resultSet.getDouble("score"));
                studentCourses.add(studentCourse);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return studentCourses;
    }

    @Override
    public double getStudentMark(int studentId, int courseId) throws SQLException {
        double mark = 0;
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_Course_Mark_BY_SQL);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                mark = resultSet.getDouble("score");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return mark;
    }

    @Override
    public double getMedian(int courseId) throws SQLException {
        double median = 0;
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_COURSE_MEDIAN_BY_ID_SQL);
            preparedStatement.setInt(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                median = resultSet.getDouble("median_val");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return median;
    }

    @Override
    public List<StudentCourse> getAllStudentCourses() throws SQLException {
        List<StudentCourse> studentCourses = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_ALL_Course_StudentS_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setStudentId(resultSet.getInt("student_id"));
                studentCourse.setCourseId(resultSet.getInt("course_id"));
                studentCourse.setMark(resultSet.getDouble("score"));
                studentCourses.add(studentCourse);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return studentCourses;
    }

    @Override
    public HashMap<String, Double> getCourseStatics(int id) throws SQLException {
        HashMap<String,Double>map = new HashMap<>();
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_Course_STATICS_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                map.put("MIN",resultSet.getDouble("MIN(score)"));
                map.put("AVG",resultSet.getDouble("AVG(score)"));
                map.put("MAX",resultSet.getDouble("MAX(score)"));
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return map;
    }
}
