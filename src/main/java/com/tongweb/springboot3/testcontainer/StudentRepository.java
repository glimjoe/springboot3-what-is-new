package com.tongweb.springboot3.testcontainer;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {

  Set<Student> findByName(String name);
}
