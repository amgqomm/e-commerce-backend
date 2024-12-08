package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String category;
    private String description;
    private Double rating;
    private String color;
    private Boolean stock;
    private String sku;
    private String code;
    private List<String> imageUrls ;
}