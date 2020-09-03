package com.take.my.time.services;

import com.take.my.time.model.Meeting;

public interface EmailService {



  void sendConfirmationCreationEmail(Meeting meeting, String string) throws Exception;


}
