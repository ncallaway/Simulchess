package com.ncallaway.schess.backend.api;

public class IllegalMoveException extends Exception {

  private static final long serialVersionUID = -2280821751671254634L;

  public IllegalMoveException() {
  }

  public IllegalMoveException(String message) {
    super(message);
  }

  public IllegalMoveException(Throwable cause) {
    super(cause);
  }

  public IllegalMoveException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalMoveException(String message, Throwable cause,
      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
