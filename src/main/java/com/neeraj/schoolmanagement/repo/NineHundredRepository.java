package com.neeraj.schoolmanagement.repo;

import com.neeraj.schoolmanagement.model.NineHundred;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NineHundredRepository extends MongoRepository<NineHundred, String> {
  NineHundred findTopByOrderByIdDesc();
}
