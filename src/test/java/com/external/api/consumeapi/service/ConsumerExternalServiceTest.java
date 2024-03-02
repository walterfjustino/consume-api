package com.external.api.consumeapi.service;

import com.external.api.consumeapi.domain.dto.ProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
public class ConsumerExternalServiceTest {

  @Autowired
  ConsumerExternalService consumerExternalService;

  @ParameterizedTest
  @CsvFileSource(files = "src/test/java/resources/stubs/mockResponseGetProductById.csv", useHeadersInDisplayName = true)
  @DisplayName("Retorna um produto pelo id")
  void getProductById(String id, String title, String price, String category, String description, String image){
    //GIVEN
    ProductDTO product = new ProductDTO(id, title, price, category, description, image);
    //WHEN
    var getProductById = consumerExternalService.getProductById(Long.valueOf(product.id()));
    //THEN
    Assertions.assertSame(product, getProductById);
    StepVerifier.create(getProductById).enableConditionalSupport(productDTO -> getProductById.single().equals(productDTO)).expectComplete();
    StepVerifier.create(getProductById).expectNext(product).expectComplete();
  }

  @ParameterizedTest
  @CsvFileSource(files = "src/test/java/resources/stubs/mockResponseGetAllProducts.csv", useHeadersInDisplayName = true)
  @DisplayName("Listar todos os produtos")
  void getAllProducts(String id, String title, String price, String category, String description, String image){
    //GIVEN
    ProductDTO product = new ProductDTO(id, title, price, category, description, image);
    //WHEN
    var getAllProducts = consumerExternalService.getAllProducts();
    //THEN
    StepVerifier.create(getAllProducts).expectNext().expectNextCount(3).expectComplete();
  }
}
