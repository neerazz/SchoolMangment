package com.neeraj.schoolmanagement.service;

import com.neeraj.schoolmanagement.model.Section;

public interface SectionService {
  public void updateReferences(Section oldSection, Section tempSection);
}
