package co.unaicorn.shipfyze.products.application.query.find;

import co.unaicorn.shipfyze.shared.domain.bus.query.Query;

public record ProductFindByIdQuery(Long id) implements Query {}
