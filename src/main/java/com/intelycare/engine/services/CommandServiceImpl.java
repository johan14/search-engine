package com.intelycare.engine.services;

import com.intelycare.engine.exceptions.BadCommandException;
import java.io.IOException;
import com.intelycare.engine.models.commands.Command;
import com.intelycare.engine.models.commands.CommandFactory;
import javax.inject.Inject;

public class CommandServiceImpl implements CommandService{

  @Inject
  public CommandServiceImpl(){}

  @Override
  public String executeCommand(String commandToBeExecuted) throws IOException, BadCommandException {
      Command command = CommandFactory.getCommand(commandToBeExecuted);
      return command.executeCommand(commandToBeExecuted);
  }

}
