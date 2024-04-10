package com.tongweb.springboot3.micrometer;

import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Observed(name = "helloController")
public class HelloController {

  @GetMapping()
  public String hello(){
    return "hello";
  }
}
