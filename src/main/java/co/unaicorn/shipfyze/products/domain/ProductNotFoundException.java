package co.unaicorn.shipfyze.products.domain;

public final class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super(String.format("Product with id <%s> not found", id));
    }
}
