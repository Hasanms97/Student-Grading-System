package com.example.elearningspringboot.Service;

import com.example.elearningspringboot.Entity.Student;
import com.example.elearningspringboot.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student getStudent(int id) {
        System.out.println(studentRepository.getById(id));
        return studentRepository.getById(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
}
