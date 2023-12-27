package com.example.hibernate.HibernatePractice;

import com.example.hibernate.HibernatePractice.Entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.hibernate.HibernatePractice.Service.StudentService;

import java.util.List;

@SpringBootApplication
public class HibernatePracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernatePracticeApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentService studentService){
		return runner->{
			//System.out.println("Hello World");
			//saveStudent(studentService);
			 //getStudent(studentService);
			// getAllStudent(studentService);
			 // getByLastName(studentService);
			//updateStudent(studentService);
			//updateAllStudent(studentService);
		//	deleteStudent(studentService);
			deleteAllStudent(studentService);
		};
	}

	private void deleteStudent(StudentService studentService) {
		studentService.deleteStudent(6);
		System.out.println("deleted with id = 6");
	}

	private void deleteAllStudent(StudentService studentService) {
		int num = studentService.deleteMultiplestudent();
		System.out.println("No of student deleted = "+num);
	}

	private void updateAllStudent(StudentService studentService) {
		int num = studentService.updateAllStudent();
		System.out.println("Number of rows updated = " +num);
	}

	private void updateStudent(StudentService studentService) {
		Student theStudent = studentService.updateStudent(3);
		System.out.println(theStudent);
	}

	private void getByLastName(StudentService studentService) {
		List<Student> students = studentService.getByLastName("kumar");
		System.out.println(students);
	}

	private void getAllStudent(StudentService studentService) {
		List<Student> students = studentService.listStudent();
		//System.out.println("List of all Students : " + students);
		for ( Student student:students) {
			System.out.println(student.getFirstName() +" " + student.getLastName()+" "+student.getEmail());
		}
	}

	private void saveStudent(StudentService studentService) {
		System.out.println("Creating Student");
		Student student = new Student("ritesh","kumar","riteshraushan0604@gmail.com");
		System.out.println("Saving the student");
		studentService.saveStudent(student);
		System.out.println("The id of the saved student is : " + student.getId());
	}


	private void getStudent(StudentService studentService){
		Student student = studentService.getStudent(1);

		System.out.println(
				"Name : "+ student.getFirstName() + " " + student.getLastName() +"  "
				+ "Email : " + student.getEmail()
		);

	}
	

}
