import com.google.inject.Guice;
import com.google.inject.Injector;
import engine.exceptions.BadCommandException;
import engine.services.CommandService;
import engine.services.CommandServiceImpl;
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
  void shouldExecuteCorrectlyQueryCommand() throws BadCommandException, IOException {
    Injector injector = Guice.createInjector();
    CommandService commandService = injector.getInstance(CommandServiceImpl.class);
    commandService.executeCommand("index 1 token");
  }

  @Test
  void shouldExecuteCorrectlyIndexCommand() throws IOException, BadCommandException {
    Injector injector = Guice.createInjector();
    CommandService commandService = injector.getInstance(CommandServiceImpl.class);
    commandService.executeCommand("query 1");
  }

}
