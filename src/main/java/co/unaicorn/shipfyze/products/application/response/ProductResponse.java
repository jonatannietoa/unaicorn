package co.unaicorn.shipfyze.products.application.response;

public record ProductResponse(
    Long id, String name, String description, Double price, String category) {}
