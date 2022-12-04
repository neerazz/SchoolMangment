package com.neeraj.schoolmanagement.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.neeraj.schoolmanagement.model.Semester;

public interface SemesterRepository extends MongoRepository<Semester, String> {
  public Semester findByYearAndSeason(String year, String season);
}
