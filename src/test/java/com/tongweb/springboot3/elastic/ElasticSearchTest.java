package com.tongweb.springboot3.elastic;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ElasticSearchTest {

  @Resource
  ElasticRepository elasticRepository;

  @Test
  void test1(){
    Student stu = new Student();
    stu.setAge(15);
    stu.setId(1L);
    stu.setName("Anne");
    stu.setContent("test");
    System.out.println(elasticRepository.save(stu));
  }
}
