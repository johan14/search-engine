package com.intelycare.engine.services;

import com.intelycare.engine.exceptions.BadCommandException;
import java.io.IOException;

public interface CommandLineService {

  public  void listenForCommands() throws IOException, BadCommandException;

}
