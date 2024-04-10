package com.tongweb.springboot3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.UseMainMethod;

@SpringBootTest(useMainMethod = UseMainMethod.ALWAYS)
class Springboot3ApplicationTests {

  @Value("${a.b}")
  private String c;

  @Test
  void contextLoads() {
    System.out.println(c);
  }

}
