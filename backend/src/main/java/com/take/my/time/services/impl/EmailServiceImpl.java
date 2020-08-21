package com.take.my.time.services.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.take.my.time.configuration.EmailCfg;
import com.take.my.time.services.EmailService;


@Service
public class EmailServiceImpl implements EmailService {

  private EmailCfg emailCfg;
  private JavaMailSenderImpl mailSender;


  public EmailServiceImpl(EmailCfg emailCfg, JavaMailSenderImpl mailSender) {
    this.emailCfg = emailCfg;
    this.mailSender = mailSender;

    mailSender.setHost(this.emailCfg.getHost());
    mailSender.setPort(this.emailCfg.getPort());
    mailSender.setUsername(this.emailCfg.getUsername());
    mailSender.setPassword(this.emailCfg.getPassword());
  }



  @Override
  public void sendConfirmationCreationEmail(String email, String guid, String secureCode)
      throws Exception {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject("Confirmation of event creation with code : " + guid);
    message.setFrom("NoReply@TakeMyTime.com");
    message.setTo(email);
    message.setText("test");

    mailSender.send(message);


  }

}
