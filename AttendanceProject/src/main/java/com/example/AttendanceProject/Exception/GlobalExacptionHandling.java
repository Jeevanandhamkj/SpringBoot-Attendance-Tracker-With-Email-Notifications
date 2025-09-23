package com.example.AttendanceProject.Exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExacptionHandling {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public Map<String,String> u(UserAlreadyExistsException us){
       Map<String,String>e=new HashMap<>();
       e.put("Error message",us.getMessage());
       return e;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String>m(MethodArgumentNotValidException ms){
        Map<String,String>ee=new HashMap<>();
        ms.getBindingResult().getFieldErrors().forEach(
                error->{
                    ee.put(error.getField(),error.getDefaultMessage());
                }
        );
        return ee;

    }
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String>id(UserNotFoundException i){
        Map<String,String>f=new HashMap<>();
        f.put("Error message",i.getMessage());
        return f;
    }
}
