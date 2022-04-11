package com.intelycare.engine.exceptions;

import com.intelycare.engine.config.ResourceBundleConfig;

public class BadCommandException extends Exception{

  public BadCommandException(String message){
    super(message + ResourceBundleConfig.getWord("failed-parsing"));
  }

}
