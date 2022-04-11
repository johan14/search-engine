package com.intelycare.engine.models.commands;

public class CommandFactory {

  public static Command getCommand(String query){
    if(query.contains("index"))
      return new IndexCommand();
    else return new QueryCommand();
  }

}
