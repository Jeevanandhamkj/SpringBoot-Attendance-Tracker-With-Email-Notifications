package com.example.AttendanceProject.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "rollnumber cannot be blank")
    private String rollnumber;
    @NotBlank(message = "class cannot be blank")
    private String studentclass;
    @NotBlank(message = "section cannot be blank")
    private String section;
    @Email(message = "Enter vaild email")
    private String email;

    public Student() {
    }

    public Student(long id, String name, String rollnumber, String studentclass, String section, String email) {
        this.id = id;
        this.name = name;
        this.rollnumber = rollnumber;
        this.studentclass = studentclass;
        this.section = section;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollnumber() {
        return rollnumber;
    }

    public void setRollnumber(String rollnumber) {
        this.rollnumber = rollnumber;
    }

    public String getStudentclass() {
        return studentclass;
    }

    public void setStudentclass(String studentclass) {
        this.studentclass = studentclass;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
