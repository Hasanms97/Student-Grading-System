package com.example.elearningspringboot.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.Mapping;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Course_Student")
public class Course_Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Course_Student_id")
    private int Course_Student_id;
    @Column(name = "course_id")
    int course_id;
    @Column(name = "student_id")
    int student_id;
    @Column(name = "score")
    double score;


}
