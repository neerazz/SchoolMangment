package com.neeraj.schoolmanagement.repo;


import java.util.List;

import com.neeraj.schoolmanagement.model.Building;
import com.neeraj.schoolmanagement.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoomRepository extends MongoRepository<Room, String> {
  public Room findById(String id);

  public Room findByName(String name);

  public List<Room> findByBuilding(Building building);

}
