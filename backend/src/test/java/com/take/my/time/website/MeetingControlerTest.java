package com.take.my.time.website;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {MeetingControlerTest.Initializer.class})
class MeetingControlerTest {

  @Autowired
  private MockMvc mvc;

  @ClassRule
  public static PostgreSQLContainer postgresContainer =
      (PostgreSQLContainer) new PostgreSQLContainer("postgres:11.1")
          .withDatabaseName("take_my_time").withUsername("root").withPassword("root")
          .withExposedPorts(5432);

  public MeetingControlerTest() throws SQLException {

    postgresContainer.start();

  }

  static class Initializer
      implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues
          .of("spring.datasource.url=" + postgresContainer.getJdbcUrl(),
              "spring.datasource.username=" + postgresContainer.getUsername(),
              "spring.datasource.password=" + postgresContainer.getPassword())
          .applyTo(configurableApplicationContext.getEnvironment());
    }
  }

  @Test
  void testAddMeeting() throws Exception {
    String URI = "/addmeeting";
    String path = "./src/test/resources/meeting.json";

    // System.out.println("Print :::::::::::::::::" + Files.readString(Paths.get(path)));

    ResultActions action = mvc.perform(MockMvcRequestBuilders.post(URI)
        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        .content(Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8).toString()));
    action.andExpect(status().isOk());

  }

  @Test
  void testGetMeeting() throws Exception {

    String URI = "/addmeeting";
    String path = "./src/test/resources/meeting.json";

    ResultActions action = mvc.perform(MockMvcRequestBuilders.post(URI)
        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        .content(Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8).toString()));
    action.andExpect(status().isOk());
    MvcResult result = action.andReturn();

    String id = result.getResponse().getContentAsString().replace("\"", "");
    String get_URI = "/getmeeting";
//			System.out.println(id);
    ResultActions get_result = mvc.perform(
        MockMvcRequestBuilders.get(get_URI).accept(MediaType.APPLICATION_JSON).param("guid", id));

    // System.out.println("XXXXXXXXXXX" +
    // get_result.getResponse().getContentAsString());
    get_result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.author").value("michel"));
  }

  /**
   * GIVEN bad
   * 
   * WHEN
   * 
   * THEN
   * 
   * @throws Exception
   */
  @Test
  void testAddWrongMeeting() throws Exception {
    String URI = "/addmeeting";
    String path = "./src/test/resources/meeting_too_big.json";

    // System.out.println("Print :::::::::::::::::" + Files.readString(Paths.get(path)));

    ResultActions action = mvc.perform(MockMvcRequestBuilders.post(URI)
        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        .content(Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8).toString()));
    action.andExpect(status().is4xxClientError());

  }



}
