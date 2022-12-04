package com.neeraj.schoolmanagement.service.impl;

import com.neeraj.schoolmanagement.model.Building;
import com.neeraj.schoolmanagement.model.Room;
import com.neeraj.schoolmanagement.repo.BuildingRepository;
import com.neeraj.schoolmanagement.repo.RoomRepository;
import com.neeraj.schoolmanagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

  @Autowired
  BuildingRepository buildingRepository;

  @Autowired
  RoomRepository roomRepository;

  @Override
  public boolean checkConflicts(Room userInputRoom) {

    Building building = buildingRepository.findOne(userInputRoom.building.id.toHexString());
    System.out.println(building);
    for (Room room : building.rooms) {
      System.out.println(room);
      if (room.name.equals(userInputRoom.name)) {
        return true;
      }
    }

    return false;

  }

}
