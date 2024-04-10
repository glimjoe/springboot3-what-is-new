package com.tongweb.springboot3.testcontainer;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Student {

  @Id
  String id;
  String name;
  int age;
}
