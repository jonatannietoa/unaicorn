package co.unaicorn.shipfyze.products.domain.exceptions;

import co.unaicorn.shipfyze.shared.domain.DomainError;

public final class ProductNotFoundException extends DomainError {
    public ProductNotFoundException(Long id) {
        super("product_not_found", String.format("The product <%s> has not been found", id));
    }
}
