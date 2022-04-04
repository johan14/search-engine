package engine.models.commands;

import com.google.inject.Guice;
import com.google.inject.Injector;
import engine.config.RestHighLevelClientConfig;
import engine.exceptions.BadCommandException;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Data;
import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.RestHighLevelClient;
import engine.utils.OpenSearchUtils;

@Data
public class QueryCommand implements Command{

  @Override
  public void validate(String command) throws BadCommandException {
    Pattern pattern = Pattern.compile("^(query)\\s((\\w))*");
    Matcher matcher = pattern.matcher(command);
    boolean matchFound = matcher.find();
    if (!matchFound) {
      throw new BadCommandException("query error");
    }
  }

  @Override
  public void executeCommand(String command) throws IOException, BadCommandException {
    validate(command);
    Injector injector = Guice.createInjector();
    RestHighLevelClientConfig restHighLevelClientConfig = injector.getInstance(RestHighLevelClientConfig.class);
    RestHighLevelClient client = restHighLevelClientConfig.provideRestHighLevelClient();
      List<String> ids = OpenSearchUtils.queryData(client, command);
      System.out.println("query results "+ids.stream().collect(Collectors.joining(" ")));

  }
}
