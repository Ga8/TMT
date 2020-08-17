package com.take.my.time.repositories;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.take.my.time.model.Meeting;
import com.take.my.time.model.Opportunity;
import com.take.my.time.model.User;
import com.take.my.time.services.impl.MeetingServiceImpl;
import com.take.my.time.services.impl.StateServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {MeetingRepositoryTest.Initializer.class})
class MeetingRepositoryTest {

  @Autowired
  private MeetingRepository meetingRepo;

  private StateServiceImpl stateService = new StateServiceImpl();

  private MeetingServiceImpl meetingService =
      new MeetingServiceImpl(meetingRepo, stateService, null);

  public MeetingRepositoryTest() {
	  postgresContainer.start();
	  
  }
  
	@ClassRule
	public static PostgreSQLContainer postgresContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:11.1")
			.withDatabaseName("take_my_time")
			.withUsername("root")
			.withPassword("root")
			.withExposedPorts(5432);

  
  static class Initializer
  implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TestPropertyValues.of(
          "spring.datasource.url=" + postgresContainer.getJdbcUrl(),
          "spring.datasource.username=" + postgresContainer.getUsername(),
          "spring.datasource.password=" + postgresContainer.getPassword()
        ).applyTo(configurableApplicationContext.getEnvironment());
    }
}
  @Test
  void SaveTest() throws JsonProcessingException {

    Meeting meeting = new Meeting();
    meeting.setTitle("Test");


    Opportunity op = new Opportunity();
    op.setOpportunityDate("2020-02-09");
    User user = new User("gg");

    op.getUsers().add(user);
    meeting.getOpportunities().add(op);

    Meeting meetingReturned = meetingRepo.save(meeting);

    Optional<Meeting> meetingReturned2 = meetingRepo.findByGuid(meeting.getGuid());

    Meeting meetingReturned3 = new Meeting();;
    if (meetingReturned2.isPresent()) {
      meetingReturned3 = meetingReturned2.get();
    }
    ObjectMapper objectMapper = new ObjectMapper();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    objectMapper.setDateFormat(df);
 //   String meeting4 = objectMapper.writeValueAsString(meetingReturned3);
  //  System.out.println(meeting4);

    Assert.assertTrue(meetingReturned3.getGuid() != null);
  }

  @Test
  public void getMeetingTest() throws JsonProcessingException {

    Meeting meeting = meetingService.mapMeeting("{\"guid\":\"\",\n" + 
    		"\"title\":\"Test\",\n" + 
    		"\"author\":\"michel\",\n" + 
    		"\"opportunities\":[{\n" + 
    		"                \"date\":\"2020-01-17\",\n" + 
    		"                \"hour\":\"11:00\",\n" + 
    		"                \"name\":\"michel\"\n" + 
    		"            },\n" + 
    		"            {\n" + 
    		"                \"date\":\"2020-01-18\",\n" + 
    		"                \"hour\":\"18:00\",\n" + 
    		"                \"name\":\"michel\"\n" + 
    		"            }]\n" + 
    		"}");


    Meeting meetingReturned = meetingRepo.save(meeting);

    Optional<Meeting> meetingReturned2 = meetingRepo.findByGuid(meeting.getGuid());

    Meeting meetingReturned3 = new Meeting();
    if (meetingReturned2.isPresent()) {
      meetingReturned3 = meetingReturned2.get();
    }
    ObjectMapper objectMapper = new ObjectMapper();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    objectMapper.setDateFormat(df);
   // String meeting4 = objectMapper.writeValueAsString(meetingReturned3);
   // System.out.println(meeting4);

    Assert.assertTrue(meetingReturned3.getGuid() != null);

    String guidString = meetingReturned3.getGuid().toString();

    UUID uuidtest = UUID.fromString(guidString);

    Assert.assertTrue(uuidtest != null);

  }



}
