package co.unaicorn.shipfyze.products.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
    @NotBlank(message = "Product name is required")
    String name,
    
    String description,
    
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    Double price,
    
    @NotBlank(message = "Category is required")
    String category
) {}