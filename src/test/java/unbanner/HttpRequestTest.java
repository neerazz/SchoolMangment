package unbanner;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import com.neeraj.schoolmanagement.model.Student;
import com.neeraj.schoolmanagement.repo.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WithMockUser
@SpringBootTest
@AutoConfigureMockMvc
public class HttpRequestTest {

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private StudentRepository repo;


  @Before
  public void setup() {
    mockMvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();
  }

  /*
   * Checks the routing for a GET request to '/'
   * Checks that the correct view has been called by the controller
   */
  @Test
  public void indexShouldRespond() throws Exception {
    this.mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("index"))
        .andDo(print());
  }

  /*
   * Checks the routing for an unauthorized GET request to '/'
   * Ensures that it is redirected to the login page
   */
  @Test
  @WithAnonymousUser
  public void anonShouldBeRedirectToLogin() throws Exception {
    this.mockMvc.perform(get("/"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrlPattern("**/login"))
        .andDo(print());
  }

  @Test
  public void helpShouldRespond() throws Exception {
    this.mockMvc.perform(get("/help"))
        .andExpect(status().isOk())
        .andExpect(view().name("help"))
        .andDo(print());
  }


  /*
   * Checks the routing for a GET request to '/students'
   * Checks that the correct view has been called by the controller
   * Checks that the correct model has been passed by the controller
   */
  @Test
  public void studentsShouldRespond() throws Exception {

    repo.deleteAll();
    repo.save(new Student("Alice", "Smith"));
    repo.save(new Student("Bob", "Smith"));

    this.mockMvc.perform(get("/students"))
        .andExpect(status().isOk())
        .andExpect(view().name("students"))
        .andExpect(model().attribute("students", hasSize(2)))
        .andExpect(model().attribute("students", hasItem(
            allOf(
                hasProperty("firstName", is("Alice")),
                hasProperty("lastName", is("Smith"))))))
        .andDo(print());
  }

  /*
   * Checks the routing for a GET request to '/students/new'
   * Checks that the correct view has been called by the controller
   */
  @Test
  public void studentsNewShouldRespond() throws Exception {
    this.mockMvc.perform(get("/students/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("create_student"))
        .andDo(print());
  }

  /*
   * Checks the routing for a POST request to '/students/new'
   * Checks that the correct view has been called by the controller
   * Checks that the correct model has been passed by the controller
   * Checks that a new student has been successfully created
   */
  @Test
  public void studentsNewShouldCreate() throws Exception {
    repo.deleteAll();
    repo.save(new Student("Alice", "Smith"));
    repo.save(new Student("Bob", "Smith"));

    this.mockMvc.perform(post("/students/new")
        .with(csrf().asHeader())
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("firstName", "Tom")
        .param("lastName", "Cruz"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/students"))
        .andDo(print());

    this.mockMvc.perform(get("/students"))
        .andExpect(status().isOk())
        .andExpect(view().name("students"))
        .andExpect(model().attribute("students", hasSize(3)))
        .andExpect(model().attribute("students", hasItem(
            allOf(
                hasProperty("firstName", is("Tom")),
                hasProperty("lastName", is("Cruz"))))))
        .andDo(print());
  }

  /*
   * Checks the routing for a GET request to '/students/{id}'
   * Checks that the correct view has been called by the controller
   * Checks that the correct model has been passed by the controller
   * Checks that the model has the correct attributes
   */
  @Test
  public void studentShouldRespond() throws Exception {
    repo.deleteAll();
    repo.save(new Student("Alice", "Smith"));
    repo.save(new Student("Bob", "Smith"));

    List<Student> stuList = repo.findAll();

    this.mockMvc.perform(get("/student/{id}", stuList.get(0).id))
        .andExpect(status().isOk())
        .andExpect(view().name("student"))
        .andExpect(model().attribute("student",
            allOf(
                hasProperty("firstName", is("Alice")),
                hasProperty("lastName", is("Smith")))))
        .andDo(print());
  }

  /*
   * Checks the routing for a DELETE request to '/students/{id}'
   * Checks that the correct view has been called by the controller
   * Checks that the student has been successfully deleted
   */
  @Test
  public void studentShouldDelete() throws Exception {
    repo.deleteAll();
    repo.save(new Student("Alice", "Smith"));
    repo.save(new Student("Bob", "Smith"));
    List<Student> stuList = repo.findAll();

    this.mockMvc.perform(delete("/student/{id}", stuList.get(0).id)
        .with(csrf().asHeader()))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/students"))
        .andDo(print());

    this.mockMvc.perform(get("/students"))
        .andExpect(status().isOk())
        .andExpect(view().name("students"))
        .andExpect(model().attribute("students", hasSize(1)))
        .andExpect(model().attribute("students", hasItem(
            allOf(
                hasProperty("firstName", is("Bob")),
                hasProperty("lastName", is("Smith"))))))
        .andDo(print());
  }

  /*
   * Checks the routing for a POST request to '/student/{id}'
   * Checks that the correct view has been called by the controller
   * Checks that a new student has been successfully updated
   */
  @Test
  public void studentShouldUpdate() throws Exception {
    repo.deleteAll();
    repo.save(new Student("Alice", "Smith"));
    repo.save(new Student("Bob", "Smith"));

    List<Student> stuList = repo.findAll();

    this.mockMvc.perform(post("/student/{id}", stuList.get(0).id).with(csrf().asHeader())
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("studentNum", "1234567")
        .param("firstName", "Tom")
        .param("lastName", "Cruz"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/student/" + stuList.get(0).id))
        .andDo(print());

    this.mockMvc.perform(get("/students"))
        .andExpect(status().isOk())
        .andExpect(view().name("students"))
        .andExpect(model().attribute("students", hasSize(2)))
        .andExpect(model().attribute("students", hasItem(
            allOf(
                hasProperty("firstName", is("Tom")),
                hasProperty("lastName", is("Cruz"))))))
        .andDo(print());
  }

}