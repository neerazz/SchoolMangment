package com.neeraj.schoolmanagement.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.neeraj.schoolmanagement.model.Student;


public interface StudentRepository extends MongoRepository<Student, String> {

  public Student findByFirstName(String firstName);

  public List<Student> findByLastName(String lastName);

  public Student findByFirstNameAndLastName(String firstName, String lastName);

}
