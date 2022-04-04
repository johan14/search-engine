package engine.exceptions;

public class BadCommandException extends Exception{

  public BadCommandException(String message){
    super(message + " Command failed to be parsed");
  }

}
