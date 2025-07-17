package co.unaicorn.shipfyze.products.application.find;

import co.unaicorn.shipfyze.products.domain.Product;
import co.unaicorn.shipfyze.products.domain.ProductRepository;
import co.unaicorn.shipfyze.products.domain.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class ProductFinder {
    private final ProductRepository repository;

    public ProductFinder(ProductRepository repository) {
        this.repository = repository;
    }

    public Product find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
