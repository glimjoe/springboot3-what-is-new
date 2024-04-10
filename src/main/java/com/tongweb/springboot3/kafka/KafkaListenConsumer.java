/***
 * 这是一个kafka的消费类demo
 * 注释是因为启动老是一堆日志，下面的代码可以随意打开注释或保持原状
 */
//package com.tongweb.springboot3.kafka;
//
//import java.util.List;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class KafkaListenConsumer {
//
//  @KafkaListener(topics = "${spring.kafka.consumer.topic}")
//  public void listen(List<ConsumerRecord<String, String>> records){
//      for (ConsumerRecord<String, String> record: records){
//        System.out.println(record.key() + ": " + record.value());
//      }
//  }
//}
