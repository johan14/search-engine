package engine;


import com.google.inject.Guice;
import com.google.inject.Injector;
import engine.config.DatabaseSeeder;
import engine.exceptions.BadCommandException;
import engine.services.CommandLineServiceImpl;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

  public static void main(String args[]) throws IOException, BadCommandException {
    Injector injector = Guice.createInjector();
    DatabaseSeeder databaseSeeder = injector.getInstance(DatabaseSeeder.class);
    databaseSeeder.seedDatabase();

    CommandLineServiceImpl commandLineServiceImpl = injector.getInstance(CommandLineServiceImpl.class);
    commandLineServiceImpl.listenForCommands();

  }
}
