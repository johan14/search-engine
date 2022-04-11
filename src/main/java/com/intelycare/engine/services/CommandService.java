package com.intelycare.engine.services;


import com.intelycare.engine.exceptions.BadCommandException;

import java.io.IOException;

public interface CommandService {

  public String executeCommand(String command) throws IOException, BadCommandException;

}
