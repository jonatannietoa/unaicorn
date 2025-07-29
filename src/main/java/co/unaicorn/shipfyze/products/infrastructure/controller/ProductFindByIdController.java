package co.unaicorn.shipfyze.products.infrastructure.controller;

import co.unaicorn.shipfyze.products.application.query.find.ProductFindByIdQuery;
import co.unaicorn.shipfyze.products.application.query.find.ProductFindByIdQueryHandler;
import co.unaicorn.shipfyze.products.application.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductFindByIdController {
  private final ProductFindByIdQueryHandler productFindByIdQueryHandler;

  public ProductFindByIdController(ProductFindByIdQueryHandler productFindByIdQueryHandler) {
    this.productFindByIdQueryHandler = productFindByIdQueryHandler;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> execute(@PathVariable Long id) {
    try {
      ProductFindByIdQuery query = new ProductFindByIdQuery(id);
      return ResponseEntity.ok(this.productFindByIdQueryHandler.handle(query));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
