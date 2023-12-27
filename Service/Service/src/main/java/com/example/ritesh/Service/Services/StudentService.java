package com.example.ritesh.Service.Services;

import com.example.ritesh.Service.Entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    public Student getStudent(Integer id);
    public List<Student> getAllStudent();
    public Student updateStudent(Student student,int id);
    public Student createStudent(Student student);
    public void deleteStudent(Integer id);
}
