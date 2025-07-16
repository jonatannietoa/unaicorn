package co.unaicorn.shipfyze.products.application.query.find;

import co.unaicorn.shipfyze.products.domain.Product;
import co.unaicorn.shipfyze.products.domain.ProductRepository;
import co.unaicorn.shipfyze.products.domain.exceptions.ProductNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductFinder {

    private final ProductRepository repository;

    public ProductFinder(ProductRepository repository) {
        this.repository = repository;
    }

    public Product findById(String id) {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        return product.get();
    }
}
