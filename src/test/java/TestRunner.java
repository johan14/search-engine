import com.google.inject.Guice;
import com.google.inject.Injector;
import com.intelycare.engine.exceptions.BadCommandException;
import com.intelycare.engine.services.CommandService;
import com.intelycare.engine.services.CommandServiceImpl;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestRunner {

  @Test
  void shouldThrowBadCommandExceptionFromIncorrectQueryCommand() throws BadCommandException, IOException {
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
    assertEquals(commandService.executeCommand("query token"), "query results 1 2");
  }

  @Test
  void shouldReturnNoResults() throws BadCommandException, IOException {
    Injector injector = Guice.createInjector();
    CommandService commandService = injector.getInstance(CommandServiceImpl.class);
    assertEquals(commandService.executeCommand("query token2").trim(), "query results");
  }

}
