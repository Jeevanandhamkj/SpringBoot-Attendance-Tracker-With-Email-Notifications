package com.example.AttendanceProject.Service;

import com.example.AttendanceProject.JWt.JwtUtils;
import com.example.AttendanceProject.AuthModel.User;
import com.example.AttendanceProject.AuthRepo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    JwtUtils jwtUtils;

    private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    //register and login

    public User reg(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String log(String username,String password){
        Optional<User>op=userRepo.findByUsername(username);
        if(op.isPresent()){
            User user=op.get();
            if(encoder.matches(password,user.getPassword())){
              return   jwtUtils.generateToken(user.getUsername());
            }else{
                throw new RuntimeException("no");
            }
        }else{
            throw new RuntimeException("no user");
        }
    }
}
