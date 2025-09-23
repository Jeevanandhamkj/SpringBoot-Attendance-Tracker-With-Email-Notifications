package com.example.AttendanceProject.Exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String msg){
        super(msg);
    }
}
