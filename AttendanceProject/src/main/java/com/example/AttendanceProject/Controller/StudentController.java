package com.example.AttendanceProject.Controller;

import com.example.AttendanceProject.Model.Student;
import com.example.AttendanceProject.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    StudentService service;
    @Autowired
    StudentController (StudentService service){
        this.service=service;
    }
   @PostMapping("/store")
   public ResponseEntity<Student>ad(@RequestBody @Valid Student s){
        return new ResponseEntity<>(service.store(s), HttpStatus.CREATED);
   }
   @PostMapping("/storeAll")
   public ResponseEntity<List<Student>>adAll(@RequestBody @Valid List<Student>s){
        return new ResponseEntity<>(service.storeAll(s),HttpStatus.CREATED);
   }
   @GetMapping("/get/{id}")
   public ResponseEntity<Student>getById(@PathVariable("id")long id){
        return ResponseEntity.ok(service.getbyid(id));
   }
   @GetMapping("/getAll")
   public ResponseEntity<List<Student>>getAll(){
        return ResponseEntity.ok(service.getAll());
   }
@PutMapping("/up/{id}")
   public ResponseEntity<Student>updating(@PathVariable("id")long id,@RequestBody Student ss){
        Student student=service.up(id,ss);
        return ResponseEntity.ok(student);

   }
   @PatchMapping("/pat/{id}")
   public ResponseEntity<Student>patching(@PathVariable("id")long id,@RequestBody Map<String,Object>k){
        Student s=service.pat(id,k);
        return ResponseEntity.ok(s);
   }
   @DeleteMapping("/del/{id}")
   public ResponseEntity<Student>del(@PathVariable("id")long id){
        return ResponseEntity.ok(service.del(id));
   }
@GetMapping("/p")
   public ResponseEntity<Page<Student>>pa(@RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(service.pagging(page,size));
   }
   @GetMapping("/s/{field}/{dir}")
   public ResponseEntity<List<Student>>sor(@PathVariable("field")String field,@PathVariable("dir")String dir){
        return ResponseEntity.ok(service.sort(field,dir));
   }
}
