package org.intelycare.engine;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.intelycare.engine.exceptions.BadCommandException;
import com.intelycare.engine.services.CommandService;
import com.intelycare.engine.services.CommandServiceImpl;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestIntegrationRunner {

  @Test
  void shouldThrowBadCommandExceptionFromIncorrectQueryCommand() {
    Injector injector = Guice.createInjector();
    CommandService commandService = injector.getInstance(CommandServiceImpl.class);
    assertThrows(BadCommandException.class, () -> commandService.executeCommand("qury a"));
  }

  @Test
  void shouldThrowBadCommandExceptionFromIncorrectIndexCommand() {
    Injector injector = Guice.createInjector();
    CommandService commandService = injector.getInstance(CommandServiceImpl.class);
    assertThrows(BadCommandException.class, () -> commandService.executeCommand("index token"));
  }

  @Test
  void shouldExecuteCorrectlyIndexCommand(){
    Injector injector = Guice.createInjector();
    CommandService commandService = injector.getInstance(CommandServiceImpl.class);
    assertDoesNotThrow(()->commandService.executeCommand("index 1 token"));
  }

  @Test
  void shouldExecuteCorrectlyQueryCommand() {
    Injector injector = Guice.createInjector();
    CommandService commandService = injector.getInstance(CommandServiceImpl.class);
    assertDoesNotThrow(()->commandService.executeCommand("query token"));
  }

  @Test
  void shouldReturnExactResults() throws BadCommandException, IOException {
    Injector injector = Guice.createInjector();
    CommandService commandService = injector.getInstance(CommandServiceImpl.class);
    commandService.executeCommand("index 1 token");
    commandService.executeCommand("index 2 token");
    assertEquals("query results 1 2", commandService.executeCommand("query token"));
  }

  @Test
  void shouldReturnNoResults() throws BadCommandException, IOException {
    Injector injector = Guice.createInjector();
    CommandService commandService = injector.getInstance(CommandServiceImpl.class);
    assertEquals("query results", commandService.executeCommand("query token2").trim());
  }

}
