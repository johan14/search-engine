package com.intelycare.engine.services;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.intelycare.engine.config.ResourceBundleConfig;
import com.intelycare.engine.exceptions.BadCommandException;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.opensearch.OpenSearchStatusException;

public class CommandLineServiceImpl implements CommandLineService{


  public CommandLineServiceImpl(){
  }

  public  void listenForCommands() throws IOException {

    Logger logger = LoggerContext.getContext().getLogger(CommandLineServiceImpl.class);
    Injector injector = Guice.createInjector();
    CommandService commandService = injector.getInstance(CommandServiceImpl.class);

    selectLanguage();
    showInstructions();

    Scanner scanner = new Scanner(System.in);
    System.out.println(ResourceBundleConfig.getWord("enter-command"));
    String command = scanner.nextLine();
    while (!command.equals("exit")){
      try{
      System.out.println(commandService.executeCommand(command));}
      catch (BadCommandException | IOException e){
        logger.error(e.getMessage());
      }catch (OpenSearchStatusException e){
        logger.error(ResourceBundleConfig.getWord("expression-error"));
      }
      System.out.println(ResourceBundleConfig.getWord("enter-command"));
      command = scanner.nextLine();
    }

    System.exit(0);
  }

  private void showInstructions() {
    System.out.println(ResourceBundleConfig.getWord("entry-instructions"));
  }

  private void selectLanguage(){
    System.out.println("Select language");
    System.out.println("  1. Press 1 for English");
    System.out.println("  2. Press 2 for Albanian");

    Scanner scanner = new Scanner(System.in);
    if(scanner.nextInt()==2)
      ResourceBundleConfig.setLocale(new Locale("al","AL"));
    }
}
