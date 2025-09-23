package com.example.AttendanceProject.Repo;

import com.example.AttendanceProject.Model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Long> {
}
