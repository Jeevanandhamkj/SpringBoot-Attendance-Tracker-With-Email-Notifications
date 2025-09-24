package com.example.AttendanceProject.Controller;

import com.example.AttendanceProject.AuthModel.User;
import com.example.AttendanceProject.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    AuthService authService;
@PostMapping("/auth/reg")
    public ResponseEntity<Map<String,String>>reg(@RequestBody User user){
        User saved=authService.reg(user);
        Map<String,String>map=Map.of("Username",saved.getUsername());
        return ResponseEntity.ok(map);

    }
    @PostMapping("/auth/log")
    public ResponseEntity<String>log(@RequestBody User user){
    return ResponseEntity.ok(authService.log(user.getUsername(),user.getPassword()));
    }
}
