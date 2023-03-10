package com.example.elearningspringboot.Repository;

import com.example.elearningspringboot.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
