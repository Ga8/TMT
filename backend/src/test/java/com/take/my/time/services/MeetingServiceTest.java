package com.take.my.time.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class MeetingServiceTest {



  @Test
  public void MapperTest() {

  }

}
