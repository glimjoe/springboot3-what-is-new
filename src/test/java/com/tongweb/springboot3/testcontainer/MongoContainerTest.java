//package com.tongweb.springboot3.testcontainer;
//
//import jakarta.annotation.Resource;
//import org.junit.Assert;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.utility.DockerImageName;
//
//@Testcontainers
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class MongoContainerTest {
//
//  @Container
//  @ServiceConnection
//  static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongodb/mongodb-community-server"));
//
//  @Resource
//  TestRestTemplate template;
//
//  @Test
//  void test1(){
//    Student stu = new Student();
//    stu.setName("Ana");
//    stu.setAge(99);
//    Student added = template.postForObject("/tc/stu", stu, Student.class);
//    Assertions.assertEquals(stu.getName(), added.getName());
//  }
//}
