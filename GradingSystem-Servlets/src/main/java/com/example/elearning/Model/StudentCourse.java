package com.example.elearning.Model;

public class StudentCourse {
    private int studentId;
    private int courseId;
    private double mark;

    public StudentCourse() {
    }

    public StudentCourse(int studentId, int courseId, double mark) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.mark = mark;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
