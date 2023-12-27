package com.example.ritesh.Service.Controllers;

import com.example.ritesh.Service.Entity.Student;
import com.example.ritesh.Service.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    StudentService studentService;
    @Autowired
    StudentController(StudentService theStudentService){
        studentService=theStudentService;
    }
    @GetMapping("/students")
    public List<Student> getStudentAll(){
        return studentService.getAllStudent();
    }
    @GetMapping("/student/{id}")
    public  Student getStudent(@PathVariable int id){
        return studentService.getStudent(id);
    }
    @PutMapping("/student/{id}")
    public Student updateStudent(@RequestBody Student student,@PathVariable int id){
        return studentService.updateStudent(student,id);
    }
    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
    @DeleteMapping("student/{id}")
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
    }
}
