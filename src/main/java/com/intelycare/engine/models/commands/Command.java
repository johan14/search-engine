package com.intelycare.engine.models.commands;

import java.io.IOException;
import com.intelycare.engine.exceptions.BadCommandException;

public interface Command {

  public void validate(String command) throws BadCommandException;

  public String executeCommand(String command) throws IOException, BadCommandException;

}
