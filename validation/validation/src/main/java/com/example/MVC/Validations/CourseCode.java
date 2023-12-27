package com.example.MVC.Validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {
    //define default course code
    public String value() default "RIT";
    //define default error message
    public String message() default "must start with RIT";

    //define default group
    public Class<?>[] groups() default {};

    //define default payloads

    public Class<? extends Payload>[] payload() default {};
}
