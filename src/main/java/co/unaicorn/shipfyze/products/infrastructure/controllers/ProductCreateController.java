package co.unaicorn.shipfyze.products.infrastructure.controllers;

import co.unaicorn.shipfyze.products.application.command.create.ProductCreateCommand;
import co.unaicorn.shipfyze.products.application.command.create.ProductCreateCommandHandler;
import co.unaicorn.shipfyze.products.application.dto.ProductRequest;
import co.unaicorn.shipfyze.products.application.dto.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductCreateController {
  private final ProductCreateCommandHandler productCreateCommandHandler;

  public ProductCreateController(ProductCreateCommandHandler productCreateCommandHandler) {
    this.productCreateCommandHandler = productCreateCommandHandler;
  }

  @PostMapping()
  public ResponseEntity<ProductResponse> execute(@Valid @RequestBody ProductRequest requestBody) {
    ProductCreateCommand command = new ProductCreateCommand(requestBody);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(this.productCreateCommandHandler.execute(command));
  }
}
