package com.external.api.consumeapi.service;

import com.external.api.consumeapi.domain.dto.ProductDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.joshworks.restclient.http.JsonNode;
import io.joshworks.restclient.http.RestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
@Slf4j
public class ConsumerExternalService {

  RestClient client = RestClient.builder().build();

  public Flux<ProductDTO> getAllProducts() {
    log.info("Start Flux getAllProducts: ");
    JsonNode jsonResponse = consumeExternalApiWithoutParam("https://fakestoreapi.com", "products");
    Type collectionType = new TypeToken<Collection<ProductDTO>>() {
    }.getType();
    Collection<ProductDTO> dtoCollection = new Gson().fromJson(jsonResponse.toString(), collectionType);
    return Flux.fromIterable(dtoCollection).log();
  }

  public Mono<ProductDTO> getProductById(Long id) {
    log.info("Start Mono getProductById: ");
    JsonNode jsonResponse = consumesExternalApi("https://fakestoreapi.com","products",id);
    Type type = new TypeToken<ProductDTO>() {}.getType();
    ProductDTO product = new Gson().fromJson(jsonResponse.toString(), type);
    return Mono.just(product).log();
  }


  private JsonNode consumesExternalApi(String baseUrl, String endpoint, Long param) {
    log.info("Build url: {}/{}/{}",baseUrl, endpoint, param);
      return client.get(baseUrl.concat("/").concat("{").concat(endpoint).concat("}").concat("/".concat(String.valueOf(param))))
              .routeParam("products", "products")
              .asJson()
              .body();
  }

  private JsonNode consumeExternalApiWithoutParam(String baseUrl, String endpoint) {
    log.info("Build url: {}/{}",baseUrl, endpoint);
    return client.get(baseUrl.concat("/").concat("{").concat(endpoint).concat("}"))
            .routeParam("products", "products")
            .asJson()
            .body();
  }
}