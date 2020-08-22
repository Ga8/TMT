package com.take.my.time.services;

import com.take.my.time.model.User;

public interface EmailService {


  void sendConfirmationCreationEmail(User user, String guid, String secureCode) throws Exception;


}
