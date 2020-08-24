package com.take.my.time.services.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.take.my.time.configuration.EmailCfg;
import com.take.my.time.model.Meeting;
import com.take.my.time.services.EmailService;


@Service
public class EmailServiceImpl implements EmailService {

  private EmailCfg emailCfg;
  private JavaMailSenderImpl mailSender;

  private static final String lineSeparator = "</BR>";


  public EmailServiceImpl(EmailCfg emailCfg, JavaMailSenderImpl mailSender) {
    this.emailCfg = emailCfg;
    this.mailSender = mailSender;

    mailSender.setHost(this.emailCfg.getHost());
    mailSender.setPort(this.emailCfg.getPort());
    mailSender.setUsername(this.emailCfg.getUsername());
    mailSender.setPassword(this.emailCfg.getPassword());
  }



  @Override
  public void sendConfirmationCreationEmail(Meeting meeting, String secureCode) throws Exception {


    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
    helper.setSubject("Confirmation of event creation : " + meeting.getTitle());
    helper.setFrom("NoReply@TakeMyTime.com");
    helper.setTo(meeting.getAuthor().getEmail());
    String text = "Hello dear " + meeting.getAuthor().getName() + "," + lineSeparator
        + "you succefully create an event with following information : " + lineSeparator
        + "<h4> Event GUID : " + meeting.getGuid() + "</h4> " + lineSeparator
        + " <h4>The owner event code : " + secureCode + "</h4> " + lineSeparator + lineSeparator
        + "Here is the link of your event : <a> http://takemytime.net/api/getmeeting?guid="
        + meeting.getGuid() + "</a>" + lineSeparator + lineSeparator + "Have a good day.";
    helper.setText(text, true);

    mailSender.send(mimeMessage);
  }

}
