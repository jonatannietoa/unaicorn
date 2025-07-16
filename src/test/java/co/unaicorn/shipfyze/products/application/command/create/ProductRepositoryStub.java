package co.unaicorn.shipfyze.products.application.command.create;

import co.unaicorn.shipfyze.products.domain.Product;
import co.unaicorn.shipfyze.products.domain.ProductRepository;

public class ProductRepositoryStub implements ProductRepository {
  @Override
  public Product save(Product product) {
    return new Product(
        1L, product.getName(), product.getDescription(), product.getPrice(), product.getCategory());
  }
}
