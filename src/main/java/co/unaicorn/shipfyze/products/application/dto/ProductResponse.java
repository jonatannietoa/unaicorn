package co.unaicorn.shipfyze.products.application.dto;

public record ProductResponse(
    Long id,
    String name,
    String description,
    Double price,
    String category
) {}