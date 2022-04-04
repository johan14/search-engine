package engine.models.commands;

import com.google.inject.Guice;
import com.google.inject.Injector;
import engine.config.RestHighLevelClientConfig;
import engine.exceptions.BadCommandException;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Data;
import engine.models.Document;
import engine.utils.OpenSearchUtils;
import org.opensearch.client.RestHighLevelClient;

@Data
public class IndexCommand implements Command {


  @Override
  public void validate(String command) throws BadCommandException {
    Pattern pattern = Pattern.compile("^(index)\\s\\d\\s((\\w)(\\s))*");
    Matcher matcher = pattern.matcher(command);
    boolean matchFound = matcher.find();
    if (!matchFound) {
      throw new BadCommandException("index error");
    }
  }

  @Override
  public void executeCommand(String command) throws IOException, BadCommandException {
    validate(command);
    Injector injector = Guice.createInjector();
    RestHighLevelClientConfig restHighLevelClientConfig= injector.getInstance(RestHighLevelClientConfig.class);

    Document document = fromCommandToDocument(command);
    OpenSearchUtils.insertData(restHighLevelClientConfig.provideRestHighLevelClient(), document);
    System.out.println("index ok "+document.getID());
  }

  private Document fromCommandToDocument(String command) {
    String ID = command.split(" ")[1];
    String tokens = Arrays.stream(command.split(" ")).skip(2).collect(Collectors.joining(" "));
    return new Document(ID, tokens);
  }
}
