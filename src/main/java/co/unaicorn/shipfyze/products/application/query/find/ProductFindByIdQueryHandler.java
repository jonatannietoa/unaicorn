package co.unaicorn.shipfyze.products.application.query.find;

import co.unaicorn.shipfyze.products.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductFindByIdQueryHandler {

    private final ProductFinder productFinder;

    public ProductFindByIdQueryHandler(ProductFinder productFinder) {
        this.productFinder = productFinder;
    }

    public Product handle(ProductFindByIdQuery query) {
        return productFinder.findById(query.id());
    }
}
