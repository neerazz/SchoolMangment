package com.neeraj.schoolmanagement.repo;


import com.neeraj.schoolmanagement.model.Building;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuildingRepository extends MongoRepository<Building, String> {
  public Building findById(String id);

  public Building findByName(String name);

}
