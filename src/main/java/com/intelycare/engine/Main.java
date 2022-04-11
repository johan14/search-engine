package com.intelycare.engine;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.intelycare.engine.config.DatabaseSeeder;
import com.intelycare.engine.config.ResourceBundleConfig;
import com.intelycare.engine.exceptions.BadCommandException;
import com.intelycare.engine.services.CommandLineServiceImpl;
import java.io.IOException;
import java.util.Locale;

public class Main {

  public static void main(String[] args) throws IOException {
    Injector injector = Guice.createInjector();
    DatabaseSeeder databaseSeeder = injector.getInstance(DatabaseSeeder.class);
    databaseSeeder.seedDatabase();

    CommandLineServiceImpl commandLineServiceImpl = injector.getInstance(CommandLineServiceImpl.class);
    commandLineServiceImpl.listenForCommands();

  }
}
