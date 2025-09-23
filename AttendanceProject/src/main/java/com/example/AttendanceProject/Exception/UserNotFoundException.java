package com.example.AttendanceProject.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String m){
        super(m);
    }
}
