package com.example.AttendanceProject.Service;

import com.example.AttendanceProject.Exception.UserAlreadyExistsException;
import com.example.AttendanceProject.Exception.UserNotFoundException;
import com.example.AttendanceProject.Model.Student;
import com.example.AttendanceProject.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

  private    StudentRepo studentRepo;
@Autowired
    StudentService(StudentRepo studentRepo){
        this.studentRepo=studentRepo;
    }

    public Student store(Student s){
        Student existing=studentRepo.findByRollnumber(s.getRollnumber());
        if(existing!=null){
            throw new UserAlreadyExistsException("User with rollnumber exist");
        }
        return studentRepo.save(s);
    }

    public List<Student> storeAll(List<Student>students){
    return studentRepo.saveAll(students);
    }

    public Student getbyid(Long id){
    Student student=studentRepo.findById(id).orElseThrow(
            ()->new UserNotFoundException("User with this id not found")
    );
    return student;
    }

    public List<Student> getAll(){
    return studentRepo.findAll();
    }

    public Student up(Long id, Student s){
    Student student=studentRepo.findById(id).orElseThrow(()->new UserNotFoundException("user with this id not founf"));
    student.setName(s.getName());
    student.setRollnumber(s.getRollnumber());
    student.setStudentclass(s.getStudentclass());
    student.setSection(s.getSection());
    student.setEmail(s.getEmail());
    return studentRepo.save(student);
    }

    public Student pat(Long id, Map<String,Object> kk){
    Student student=studentRepo.findById(id).orElseThrow(()->new UserNotFoundException("user with this id not founf"));

    kk.forEach((key,value)->{
        switch (key){
            case "name":student.setName((String) value);
            break;
            case "rollnumber":student.setRollnumber((String) value);
            break;
            case "studentclass":student.setStudentclass((String) value);
            break;
            case "section":student.setSection((String) value);
            break;
            case "email":student.setEmail((String) value);
            break;
        }
    });
    return studentRepo.save(student);

    }

    public Student del(Long id){
 Student student=studentRepo.findById(id).orElseThrow(()->new UserNotFoundException("no"));
  studentRepo.delete(student);
  return student;
    }

    public Page<Student> pagging(int page, int size){
        Pageable pageable= PageRequest.of(page,size);
        return  studentRepo.findAll(pageable);
    }

    public List<Student>sort(String field,String dir){
    Sort sort=dir.equalsIgnoreCase("desc")?Sort.by(field).descending()
            :Sort.by(field).ascending();
    return studentRepo.findAll(sort);
    }

}
