package com.tongweb.springboot3.kafka;

import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

@SpringBootTest
public class KafkaProducerTest {

  @Resource
  KafkaTemplate<String, String> template;

  @Test
  void test(){
    CompletableFuture<SendResult<String, String>> completableFuture = template.send("quichstart-events", UUID.randomUUID().toString(), "this is a test msg");
    completableFuture.thenAccept(System.out::println);
    completableFuture.exceptionally(
        e -> {
          e.printStackTrace();
          return null;
        });
  }
}
