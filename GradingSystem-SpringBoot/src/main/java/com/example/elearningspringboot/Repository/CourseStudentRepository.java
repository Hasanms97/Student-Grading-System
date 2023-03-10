package com.example.elearningspringboot.Repository;

import com.example.elearningspringboot.Entity.Course_Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface CourseStudentRepository extends JpaRepository<Course_Student,Integer> {
    static final String SELECT_Course_Student_BY_STUDENT_ID_SQL = "select * from Course_Student where student_id =?1";
    static final String SELECT_COURSE_MEDIAN_BY_ID_SQL = "SELECT AVG(dd.score) as median_val\n" +
            "FROM (\n" +
            "         SELECT d.score, @rownum\\:=@rownum+1 as `row_number`, @total_rows\\:=@rownum\n" +
            "         FROM Course_Student d, (SELECT @rownum\\:=0) r\n" +
            "         WHERE d.course_id=?1\n" +
            "         ORDER BY d.score\n" +
            "     ) as dd\n" +
            "WHERE dd.row_number IN ( FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2) ); ";
    static final String SELECT_MAX_SCORE_BY_COURSE_ID = "select score from Course_Student WHERE course_id=?1 order by score desc limit 0,1 ";
    static final String SELECT_MIN_SCORE_BY_COURSE_ID = "select score from Course_Student WHERE course_id=?1 order by score ASC limit 0,1 ";
    static final String SELECT_AVG_SCORE_BY_COURSE_ID = "select AVG(score) from Course_Student WHERE course_id=?1 ";
    static final String SELECT_COURSE_MARK_BY_STUDENT_ID_SQL = "select score from Course_Student where student_id =?1 AND course_id =?2 ";
    @Query(value = SELECT_Course_Student_BY_STUDENT_ID_SQL, nativeQuery = true)
    List<Course_Student> getStudentCoursesById(int id);
    @Query(value = SELECT_COURSE_MEDIAN_BY_ID_SQL, nativeQuery = true)
    int getMedian(int courseId);

    @Query(value = SELECT_COURSE_MARK_BY_STUDENT_ID_SQL, nativeQuery = true)
    double getStudentMark(int studentId, int courseId);
    @Query(value = SELECT_AVG_SCORE_BY_COURSE_ID, nativeQuery = true)
    double getAverageCourseScore(int courseId);
    @Query(value = SELECT_MIN_SCORE_BY_COURSE_ID, nativeQuery = true)
    double getMinimumCourseScore(int courseId);
    @Query(value = SELECT_MAX_SCORE_BY_COURSE_ID, nativeQuery = true)
    double getMaximumCourseScore(int courseId);

}
