package com.intelycare.engine.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.intelycare.engine.models.Document;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.action.update.UpdateRequest;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.unit.TimeValue;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.search.SearchHit;
import org.opensearch.search.SearchHits;
import org.opensearch.search.builder.SearchSourceBuilder;

public class OpenSearchUtils {

  private static final String INDEX_NAME = "search-engine";

  public static void insertData(RestHighLevelClient client, Document document) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    IndexRequest request = new IndexRequest(INDEX_NAME);
    String existingId = queryByDocId(client, document.getID());
    if (existingId != null) {
      UpdateRequest updateRequest = new UpdateRequest(INDEX_NAME, existingId)
          .doc(objectMapper.convertValue(document, HashMap.class));
      client.update(updateRequest, RequestOptions.DEFAULT);
    } else {
      request.source(objectMapper.convertValue(document, HashMap.class));
      client.index(request, RequestOptions.DEFAULT);
    }
  }

  public static List<String> queryData(RestHighLevelClient client, String query)
      throws IOException {

    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
        .from(0)
        .size(100)
        .timeout(new TimeValue(60, TimeUnit.SECONDS));
    String luceneQuery = query.replace("&", "AND").replace("|", "OR");
    luceneQuery = "tokens: " + luceneQuery;
    searchSourceBuilder
        .query(QueryBuilders.queryStringQuery(luceneQuery)).fetchSource(new String[]{"ID"}, null);
    SearchRequest searchRequest = new SearchRequest()
        .indices(INDEX_NAME)
        .source(searchSourceBuilder);
    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
    SearchHits hits = searchResponse.getHits();
    SearchHit[] searchHits = hits.getHits();
    List<String> results = new ArrayList<>();
    for (SearchHit hit : searchHits) {
      String id = hit.getSourceAsMap().get("ID").toString();
      results.add(id);
    }
    results = results.stream().map(id -> Integer.parseInt(id)).sorted().map(id -> id.toString()).collect(Collectors.toList());
    return results;
  }

  public static String queryByDocId(RestHighLevelClient client, String id)
      throws IOException {
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
        .from(0)
        .size(100)
        .timeout(new TimeValue(60, TimeUnit.SECONDS));
    String query = "ID: " + id;
    searchSourceBuilder
        .query(QueryBuilders.queryStringQuery(query));
    SearchRequest searchRequest = new SearchRequest()
        .indices(INDEX_NAME)
        .source(searchSourceBuilder);
    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
    SearchHits hits = searchResponse.getHits();
    SearchHit[] searchHits = hits.getHits();
    String resultId = null;
    for (SearchHit hit : searchHits) {
      resultId = hit.getId();
    }
    return resultId;
  }

}
