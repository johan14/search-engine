package engine.services;

import engine.exceptions.BadCommandException;
import java.io.IOException;

public interface CommandLineService {

  public  void listenForCommands() throws IOException, BadCommandException;

}
