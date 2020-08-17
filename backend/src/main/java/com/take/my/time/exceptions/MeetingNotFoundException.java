package com.take.my.time.exceptions;

public class MeetingNotFoundException extends RuntimeException {


  private static final long serialVersionUID = -1013848707189050880L;


  private static String message = "Meeting not found";



  public MeetingNotFoundException() {
    super(message);

  }


  public MeetingNotFoundException(Throwable cause) {
    super(cause);

  }



}
