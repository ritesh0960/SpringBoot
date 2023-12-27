package com.example.MVC.Models;

import com.example.MVC.Validations.CourseCode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Customer {
    private String firstName;
    @NotNull(message = "is require")
    @Size(min = 3,max = 12,message = "Size should be in the range of 3 to 12")
    private String lastName;

    @Min(value = 1,message = "Minimum value is 1")
    @Max(value = 100,message = "Maximum value is 100")
    @NotNull(message = "is required")
    private Integer number;

    @CourseCode(value = "COU",message = "Course Code starts with COU")
    private String courseCode;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
}
