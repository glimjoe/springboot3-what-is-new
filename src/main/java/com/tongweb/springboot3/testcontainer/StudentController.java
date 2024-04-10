package com.tongweb.springboot3.testcontainer;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tc/stu")
public class StudentController {

  @Resource
  StudentRepository repository;

  @PostMapping
  public Student add(@RequestBody Student student){
    return repository.save(student);
  }
}
