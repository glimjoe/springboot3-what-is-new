package com.tongweb.springboot3.controller;


import java.net.http.HttpClient;
import org.springframework.http.client.reactive.JdkClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("httpclient")
public class HttpClientController {

  @GetMapping()
  public String hello(){
    return "hello";
  }

  public static void main(String[] args){
    HttpClient client = HttpClient.newHttpClient();
    WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:8080")
        .clientConnector(new JdkClientHttpConnector(client))
        .build();
//    webClient.get().uri("/httpclient").retrieve().bodyToMono(String.class).subscribe(System.out::println);
    Mono<String> result = webClient.get().uri("/httpclient").retrieve().bodyToMono(String.class);
    System.out.println(result.block());
  }
}
