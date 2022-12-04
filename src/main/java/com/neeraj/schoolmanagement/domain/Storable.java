package com.neeraj.schoolmanagement.domain;


import org.bson.types.ObjectId;

public interface Storable {

  public ObjectId getId();

  public void setId(ObjectId id);
}
