package com.external.api.consumeapi.controller;

import com.external.api.consumeapi.domain.dto.ProductDTO;
import com.external.api.consumeapi.service.ConsumerExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("api/v1")
public class ProductController {

  @Autowired
  private ConsumerExternalService service;

  @GetMapping(value = "/products")
  public ResponseEntity<Flux<ProductDTO>> getAllProducts() throws Exception {
    var response =  service.getAllProducts().log();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(value = "/products/{id}")
  public ResponseEntity<Mono<ProductDTO>> getProductById(@PathVariable Long id ) throws Exception {
    Optional<Long> optId = Optional.of(id);
    var response =  service.getProductById(optId.get()).log();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
