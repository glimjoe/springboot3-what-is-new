package com.tongweb.springboot3.elastic;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticRepository extends ElasticsearchRepository<Student, Long> {
}
