package com.neeraj.schoolmanagement.service;

import com.neeraj.schoolmanagement.model.Room;

public interface RoomService {
  public boolean checkConflicts(Room userInputRoom);
}
