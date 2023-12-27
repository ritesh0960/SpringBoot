package com.example.hibernate.HibernatePractice.Service;

import com.example.hibernate.HibernatePractice.Entity.Student;

import java.util.List;

public interface StudentService {

    //to save one student
    public void saveStudent(Student student);

    //to get one student
    public Student getStudent(int id);

    //to get multiple student
    public List<Student> listStudent();

    //to get student based on lastname

    public List<Student> getByLastName(String lastName);

    //to update one student

    public  Student updateStudent(int id);

    //to update multiple students
    public int updateAllStudent();

    //to delete on student

    public void deleteStudent(int id);

    //to delete multiple student
    public int deleteMultiplestudent();
}
