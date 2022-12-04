package com.neeraj.schoolmanagement.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import com.neeraj.schoolmanagement.domain.Storable;


public class Building implements Storable {


  @Id
  public ObjectId id;

  public String name;
  public String description;
  @DBRef(lazy = true)
  public List<Room> rooms = new ArrayList<>();

  public Building() {
    this.name = "";
    this.description = "";
  }

  public Building(String name, String description) {
    this.name = name;
    this.description = description;
  }

  @Override
  public ObjectId getId() {
    return this.id;
  }

  @Override
  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {

    return String.format("Building[id=%s, description='%s']", id, description);
  }
}

