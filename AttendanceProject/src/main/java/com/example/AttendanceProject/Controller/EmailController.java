package com.example.AttendanceProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    JavaMailSender javaMailSender;

    @GetMapping("/Att/test-mail")
    public ResponseEntity<String> testMail() {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo("jeevakathirvel1999@gmail.com"); // replace with a real email
            mail.setSubject("Test Email from AttendanceProject");
            mail.setText("This is a test email to verify SMTP configuration.");
            javaMailSender.send(mail);
            return ResponseEntity.ok("Test mail sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Test mail failed: " + e.getMessage());
        }
    }

}
