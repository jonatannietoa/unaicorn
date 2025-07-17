package co.unaicorn.shipfyze.products.application.command.find;

import co.unaicorn.shipfyze.shared.domain.bus.query.Query;

public class ProductFindByIdQuery implements Query {
    private final Long id;

    public ProductFindByIdQuery(Long id) {
        this.id = id;
    }

    public Long id() {
        return id;
    }
}
