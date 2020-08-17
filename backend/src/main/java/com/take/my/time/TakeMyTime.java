package com.take.my.time;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication(scanBasePackages = "com.take.my.time")
@EnableJpaRepositories(basePackages = "com.take.my.time.repositories")
public class TakeMyTime {

  public static void main(String[] args) {
    SpringApplication.run(TakeMyTime.class, args);
  }

}
