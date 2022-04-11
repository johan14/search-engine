package com.intelycare.engine.models.commands;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.intelycare.engine.config.ResourceBundleConfig;
import com.intelycare.engine.config.RestHighLevelClientConfig;
import com.intelycare.engine.exceptions.BadCommandException;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Data;
import org.opensearch.client.RestHighLevelClient;
import com.intelycare.engine.utils.OpenSearchUtils;

@Data
public class QueryCommand implements Command{

  @Override
  public void validate(String command) throws BadCommandException {
    Pattern pattern = Pattern.compile("^(query)\\s((\\w))*");
    Matcher matcher = pattern.matcher(command);
    boolean matchFound = matcher.find();
    long operatorCount = command.chars().filter(c -> c == '&' || c == '|').count();
    long bracketsCount = command.chars().filter(c -> c == '(').count();
    if (!matchFound && (bracketsCount==(operatorCount-1))) {
      throw new BadCommandException(ResourceBundleConfig.getWord("query-error"));
    }
  }

  @Override
  public String executeCommand(String command) throws IOException, BadCommandException {
    validate(command);
    Injector injector = Guice.createInjector();
    RestHighLevelClientConfig restHighLevelClientConfig = injector.getInstance(RestHighLevelClientConfig.class);
    RestHighLevelClient client = restHighLevelClientConfig.provideRestHighLevelClient();
      List<String> ids = OpenSearchUtils.queryData(client, command);
      return "query results "+ String.join(" ", ids);
  }
}
