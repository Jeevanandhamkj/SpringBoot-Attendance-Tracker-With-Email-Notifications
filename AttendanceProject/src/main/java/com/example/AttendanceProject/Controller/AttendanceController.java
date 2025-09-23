package com.example.AttendanceProject.Controller;

import com.example.AttendanceProject.Model.Attendance;
import com.example.AttendanceProject.Service.AttendanceService;
import com.example.AttendanceProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AttendanceController {

    AttendanceService attendanceService;
    StudentService studentService;
    @Autowired
    AttendanceController(AttendanceService attendanceService,StudentService studentService){
        this.attendanceService=attendanceService;
        this.studentService=studentService;

    }
    @PostMapping("/Att/mark")
    public ResponseEntity<Attendance>ad(@RequestParam long id,@RequestParam String status,@RequestParam String date){
       LocalDate date2=LocalDate.parse(date);
        return new ResponseEntity<>(attendanceService.marking(id,status,date2), HttpStatus.CREATED);
    }
    @GetMapping ("/Att/all")
    public ResponseEntity<List<Attendance>>get(@RequestParam String date){
        LocalDate date1=LocalDate.parse(date);
        return ResponseEntity.ok(attendanceService.getAbsentStudent(date1));
    }
    @GetMapping("/att/dash")
public Map<String,Object>dash(@RequestParam (required = false) String date){
        Map<String,Object>map=new HashMap<>();
        int totalStudentCount=studentService.getAll().size();
        LocalDate checkDate;
        if(date==null || date.isBlank()){
            checkDate=LocalDate.now();
        }else{
            checkDate=LocalDate.parse(date);
        }
        List<Attendance>getAbsentStudent=attendanceService.getAbsentStudent(checkDate);
        map.put("Total Student count =",totalStudentCount);
        map.put("TotalAbsentStudent count =",getAbsentStudent.size());
        map.put("List of Absent studnet=",getAbsentStudent);
        return map;
}
}
