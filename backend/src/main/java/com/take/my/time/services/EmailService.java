package com.take.my.time.services;

public interface EmailService {


  void sendConfirmationCreationEmail(String email, String guid, String secureCode) throws Exception;


}
