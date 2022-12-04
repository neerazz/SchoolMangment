package com.neeraj.schoolmanagement.repo;


import java.util.List;

import com.neeraj.schoolmanagement.model.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProfessorRepository extends MongoRepository<Professor, String> {

  public Professor findByFirstName(String firstName);

  public List<Professor> findByLastName(String lastName);

  public Professor findByFirstNameAndLastName(String firstName, String lastName);

  public Professor findById(String id);

}


