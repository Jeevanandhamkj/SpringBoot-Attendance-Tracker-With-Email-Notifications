package com.example.AttendanceProject.Repo;

import com.example.AttendanceProject.Model.Student;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    Student findByRollnumber(String rollnumber);
}
