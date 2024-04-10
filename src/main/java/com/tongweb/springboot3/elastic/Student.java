package com.tongweb.springboot3.elastic;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "stu")
public class Student {

  @Id
  long id;
  String name;
  @Field(type = FieldType.Text, analyzer = "stu_analyzer")
  String content;
  int age;
}
