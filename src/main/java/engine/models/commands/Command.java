package engine.models.commands;

import java.io.IOException;
import engine.exceptions.BadCommandException;

public interface Command {

  public void validate(String command) throws BadCommandException;

  public void executeCommand(String command) throws IOException, BadCommandException;

}
