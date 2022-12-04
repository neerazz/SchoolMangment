package com.neeraj.schoolmanagement.service.impl;

import com.neeraj.schoolmanagement.model.NineHundred;
import com.neeraj.schoolmanagement.repo.NineHundredRepository;
import com.neeraj.schoolmanagement.service.NineHundredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class NineHundredServiceImpl implements NineHundredService {

  @Autowired
  private NineHundredRepository nineHundredRepository;

  @Override
  public int getNext() {
    NineHundred last = nineHundredRepository.findTopByOrderByIdDesc();
    int lastNum = last.seq;
    NineHundred next = new NineHundred(lastNum + 1);
    nineHundredRepository.save(next);
    return next.seq;
  }
}
