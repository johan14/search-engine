package com.intelycare.engine.config;

import java.io.IOException;
import javax.inject.Inject;
import org.opensearch.client.IndicesClient;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.indices.GetIndexRequest;

public class DatabaseSeeder {

  private final RestHighLevelClientConfig rest;
  private final String INDEX_NAME = "search-engine";

  @Inject
  public DatabaseSeeder(RestHighLevelClientConfig restHighLevelClientConfig){
    this.rest = restHighLevelClientConfig;
  }

  public void seedDatabase() throws IOException {
    IndicesClient indicesClient = rest.provideRestHighLevelClient().indices();
    GetIndexRequest getIndexRequest = new GetIndexRequest(INDEX_NAME);
    if(!indicesClient.exists(getIndexRequest, RequestOptions.DEFAULT)){
      CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX_NAME);
      rest.provideRestHighLevelClient().indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }
  }

}
