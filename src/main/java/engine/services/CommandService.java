package engine.services;


import engine.exceptions.BadCommandException;
import java.io.IOException;

public interface CommandService {

  public void executeCommand(String command) throws IOException, BadCommandException;

}
