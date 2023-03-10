package com.example.elearningspringboot.Repository;

import com.example.elearningspringboot.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
