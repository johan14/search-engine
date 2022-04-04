package engine.services;

import com.google.inject.Guice;
import com.google.inject.Injector;
import engine.exceptions.BadCommandException;
import java.io.IOException;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class CommandLineServiceImpl implements CommandLineService{


  public CommandLineServiceImpl(){
  }

  public  void listenForCommands() throws IOException {

    Logger logger = LoggerContext.getContext().getLogger(CommandLineServiceImpl.class);
    Injector injector = Guice.createInjector();
    CommandService commandService = injector.getInstance(CommandServiceImpl.class);


    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter your command");
    String command = scanner.nextLine();
    while (!command.equals("exit")){
      try{
      commandService.executeCommand(command);}
      catch (BadCommandException e){
        logger.error(e.getMessage());
      }
      System.out.println("Please enter your command");
      command = scanner.nextLine();
    }
  }
}
