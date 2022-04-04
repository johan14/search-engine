package engine.services;

import engine.exceptions.BadCommandException;
import java.io.IOException;
import engine.models.commands.Command;
import engine.models.commands.CommandFactory;
import javax.inject.Inject;

public class CommandServiceImpl implements CommandService{

  @Inject
  public CommandServiceImpl(){}

  @Override
  public void executeCommand(String commandToBeExecuted) throws IOException, BadCommandException {
      Command command = CommandFactory.getCommand(commandToBeExecuted);
      command.executeCommand(commandToBeExecuted);
  }


}
