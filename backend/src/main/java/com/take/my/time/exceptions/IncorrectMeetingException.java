package com.take.my.time.exceptions;

public class IncorrectMeetingException extends RuntimeException {

  private static final long serialVersionUID = 3682308842771887733L;

  private static final String message = "Used unauthorised body";

  public IncorrectMeetingException() {
    super();
  }

  public IncorrectMeetingException(Throwable arg0) {
    super(arg0);

  }

  public IncorrectMeetingException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
    super(arg0, arg1, arg2, arg3);

  }

  public IncorrectMeetingException(String arg0, Throwable arg1) {
    super(arg0, arg1);

  }

  public IncorrectMeetingException(String arg0) {
    super(message);
  }



}
