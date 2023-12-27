package com.example.EmployeeCRUD.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // @Size(max = 10,min = 3,message = "A minimum of 3 and maximum of 10 characters required")
    @NotNull(message = "first Name is required")
    @Column(name = "first_name")
    private String firstName="";
    @Column(name = "last_name")
    private String lastName;


    // @Email(message = "Email id is not valid")
    //@UniqueElements(message = "Email Id is already present")
    @NotNull(message = "Email Id is compulsory")
    @Column(name = "email")
    private String email="";

    public Employee() {
    }

    ;

    public Employee(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
