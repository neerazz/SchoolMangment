package com.neeraj.schoolmanagement.repo;

import java.util.List;

import com.neeraj.schoolmanagement.model.Course;
import com.neeraj.schoolmanagement.model.Professor;
import com.neeraj.schoolmanagement.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.neeraj.schoolmanagement.model.Section;
import com.neeraj.schoolmanagement.model.Semester;
import com.neeraj.schoolmanagement.model.Student;

public interface SectionRepository extends MongoRepository<Section, String> {

  public Section findById(String id);

  /*
   * returns a list of sections containing a particular course
   */
  public List<Section> findByCourse(Course course);

  /*
   * returns a list of sections containing a particular student
   */
  public List<Section> findByStudentsIn(Student student);

  public List<Section> findBySemester(Semester semester);

  public List<Section> findByProfessorIn(Professor professor);

  public List<Section> findByRoom(Room room);

}

