package com.example.AttendanceProject.Service;

import com.example.AttendanceProject.Exception.UserNotFoundException;
import com.example.AttendanceProject.Model.Attendance;
import com.example.AttendanceProject.Model.Student;
import com.example.AttendanceProject.Repo.AttendanceRepo;
import com.example.AttendanceProject.Repo.StudentRepo;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {
 StudentRepo studentRepo;
    AttendanceRepo attendanceRepo;

    JavaMailSender javaMailSender;

    AttendanceService(AttendanceRepo attendanceRepo,JavaMailSender javaMailSender,StudentRepo studentRepo){
        this.attendanceRepo=attendanceRepo;
        this.javaMailSender=javaMailSender;
        this.studentRepo=studentRepo;
    }

    public Attendance marking(Long id,String status,LocalDate date){
        Student student=studentRepo.findById(id).orElseThrow(()-> new  UserNotFoundException("user not found"));
        Attendance attendance=new Attendance();
        attendance.setStudent(student);
        attendance.setDate(date);
        attendance.setStatus(status);
        Attendance save=attendanceRepo.save(attendance);
        if("ABSENT".equalsIgnoreCase(status)){
            mailToParent(student,date);
        }
        return save;
    }

    private void mailToParent(Student student, LocalDate date){
        if(student.getEmail()==null || student.getEmail().isBlank()){
            System.out.println("not found email");
            return;
        }
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(student.getEmail());
        mailMessage.setSubject("Attendance Report");
        mailMessage.setText("Your child with Roll number"+student.getRollnumber()+"name"+student.getName()
        +"is absent on "+date+"'");
        javaMailSender.send(mailMessage);

    }

    public List<Attendance> getAbsentStudent(LocalDate date){
        return attendanceRepo.findAll().stream().filter(a->a.getDate().equals(date)&&
                "ABSENT".equalsIgnoreCase(a.getStatus())
        ).toList();
    }
}
