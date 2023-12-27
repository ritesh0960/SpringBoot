package com.example.hibernate.HibernatePractice.ServiceImpl;

import com.example.hibernate.HibernatePractice.Entity.Student;
import com.example.hibernate.HibernatePractice.Service.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentServiceImpl implements StudentService {
    private EntityManager entityManager;

    public StudentServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveStudent(Student student) {
          this.entityManager.persist(student);
    }

    @Override
    public Student getStudent(int id) {
        Student tempStudent = this.entityManager.find(Student.class,id);
//        Student student=new Student();
//        student.setFirstName(tempStudent.getFirstName());
//        student.setLastName(tempStudent.getLastName());
//        student.setEmail(tempStudent.getEmail());
        return tempStudent;
    }

    @Override
    public List<Student> listStudent() {
        TypedQuery<Student> theQuery = this.entityManager.createQuery("from Student",Student.class);
        List<Student> students = theQuery.getResultList();
        return students;
    }

    @Override
    public List<Student> getByLastName(String lastName) {
        TypedQuery<Student> theQuery = this.entityManager.createQuery("from Student where lastName=:lastName",Student.class);
        theQuery.setParameter("lastName",lastName);
        List<Student> students = theQuery.getResultList();
        return students;
    }

    @Override
    @Transactional
    public Student updateStudent(int id) {
        Student theStudent = this.entityManager.find(Student.class,id);
        theStudent.setEmail("test@gmail.com");
        this.entityManager.merge(theStudent);
        return theStudent;
    }

    @Override
    @Transactional
    public int updateAllStudent() {
        int num = this.entityManager.createQuery("UPDATE Student SET email='test1234@gmail.com' WHERE email='riteshraushan0960@gmail.com'").executeUpdate();
        return num;
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
      Student theStudent = this.entityManager.find(Student.class,id);
      this.entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteMultiplestudent() {
        int num = this.entityManager.createQuery("DELETE FROM Student where email='test1234@gmail.com'").executeUpdate();
    //    int num = theQuery.executeUpdate();
        return num;
    }
}
