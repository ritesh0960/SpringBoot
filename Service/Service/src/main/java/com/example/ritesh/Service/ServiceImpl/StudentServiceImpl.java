package com.example.ritesh.Service.ServiceImpl;

import com.example.ritesh.Service.Entity.Student;
import com.example.ritesh.Service.ExceptionHandler.StudentExceptionHandler;
import com.example.ritesh.Service.ExceptionHandler.StudentNotFoundException;
import com.example.ritesh.Service.Repository.StudentRepository;
import com.example.ritesh.Service.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    @Autowired
    StudentServiceImpl(StudentRepository theStudentRepository){

        this.studentRepository=theStudentRepository;
    }
    @Override
    public Student getStudent(Integer id) {
        Student theStudent;
        Optional<Student> student = studentRepository.findById(id);
        //optional handles the result returned by findbyid if it return the null, it is the fearure of java8
        if(student.isPresent()){
            theStudent=student.get();
        }
        else {
            throw new StudentNotFoundException("Student is not present with id " + id);
        }
        return theStudent;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student,int id) {
        Student newstudent = new Student();
        Optional<Student> theStudent = studentRepository.findById(id);
        if(theStudent.isPresent()){
            theStudent.get().setFirstName(student.getFirstName());
            theStudent.get().setLastName(student.getLastName());
            theStudent.get().setEmail(student.getEmail());
            studentRepository.save(theStudent.get());
        }
        else{
            throw new StudentNotFoundException("Student is not present with Id" + student.getId());
        }
        return theStudent.get();
    }

    @Override
    public Student createStudent(Student student) {
        Student newStudent = new Student();
        newStudent.setFirstName(student.getFirstName());
        newStudent.setLastName(student.getLastName());
        newStudent.setEmail(student.getEmail());
        studentRepository.save(newStudent);

        return newStudent;
    }

    @Override
    public void deleteStudent(Integer id) {
         Student student = new Student();
         Optional<Student> theStudent = studentRepository.findById(id);
         if(theStudent.isPresent()){
             student=theStudent.get();
             studentRepository.delete(student);
         }
         else {
             throw new StudentNotFoundException("Student is not present with id "+ id);
         }

    }
}
