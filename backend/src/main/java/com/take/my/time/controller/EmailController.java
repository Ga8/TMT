package com.take.my.time.controller;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.take.my.time.services.EmailService;

@RestController
@RequestMapping("email/")
public class EmailController {

  EmailService emailService;



  public EmailController(EmailService emailService) {
    this.emailService = emailService;
  }



  @Transactional
  @PostMapping(path = "/test", consumes = "application/json", produces = "application/json")
  public String emailMe(@RequestBody String email) {

    try {
      emailService.sendConfirmationCreationEmail("guibaud_gaetan@hotmail.fr", "dede", "hehe");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      System.out.println(e.getMessage());
      return "NOK";
    }

    return "OK";
  }



}
