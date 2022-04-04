package engine.config;

import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.Getter;
import org.apache.http.HttpHost;
import org.opensearch.client.IndicesClient;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.indices.GetIndexRequest;

public class RestHighLevelClientConfig {

  private static RestHighLevelClient restHighLevelClient;
  private final PropertiesHolder propertiesHolder;
  @Getter
  private final String INDEX_NAME = "search-engine";

  @Inject
  @Singleton
  public RestHighLevelClientConfig(PropertiesHolder propertiesHolder){
    this.propertiesHolder = propertiesHolder;
  }

  public  RestHighLevelClient provideRestHighLevelClient() {
    if (restHighLevelClient == null) {
      RestClientBuilder builder = RestClient.builder(new HttpHost(this.propertiesHolder.getProp().getProperty("host")
          , Integer.parseInt(this.propertiesHolder.getProp().getProperty("port"))
          , this.propertiesHolder.getProp().getProperty("scheme")));
      restHighLevelClient = new RestHighLevelClient(builder);
    }
    return restHighLevelClient;
  }

  public PropertiesHolder getPropertiesHolder() {
    return propertiesHolder;
  }


}
